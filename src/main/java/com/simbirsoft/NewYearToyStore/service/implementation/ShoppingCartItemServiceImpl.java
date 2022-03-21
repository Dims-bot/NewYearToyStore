package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.exceptions.EntityNotFoundException;
import com.simbirsoft.NewYearToyStore.exceptions.EntityUniqueException;
import com.simbirsoft.NewYearToyStore.mappers.ShoppingCartItemMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartItemDto;
import com.simbirsoft.NewYearToyStore.models.entity.NewYearToy;
import com.simbirsoft.NewYearToyStore.models.entity.ShoppingCartItem;
import com.simbirsoft.NewYearToyStore.repository.abstracts.NewYearToyRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.ShoppingCartItemRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.ShoppingCartRepository;
import com.simbirsoft.NewYearToyStore.service.ShoppingCartItemService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

    ShoppingCartItemMapper shoppingCartItemMapper;
    ShoppingCartItemRepository shoppingCartItemRepository;
    NewYearToyRepository newYearToyRepository;
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartItemServiceImpl(ShoppingCartItemMapper shoppingCartItemMapper,
                                       ShoppingCartItemRepository shoppingCartItemRepository,
                                       NewYearToyRepository newYearToyRepository,
                                       ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartItemMapper = shoppingCartItemMapper;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.newYearToyRepository = newYearToyRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Override
    public void saveShoppingCartItem(ShoppingCartItemDto shoppingCartItemDto) {
        if (!newYearToyRepository.existsById(shoppingCartItemDto.getNewYearToyId())) {
            throw new EntityNotFoundException("The toy does not exist");
        }
        if (!shoppingCartRepository.existsById(shoppingCartItemDto.getShoppingCartId())) {
            throw new EntityNotFoundException("The shopping cart does not exist");
        }
        Set<ShoppingCartItem> shoppingCartItems =
                shoppingCartItemRepository.getShoppingCartItemsByShoppingCartId(shoppingCartItemDto.getShoppingCartId());
        boolean isPresentSameShoppingCartItemInDb = shoppingCartItems.stream()
                .map(ShoppingCartItem::getNewYearToy)
                .map(NewYearToy::getId)
                .anyMatch(x -> x.equals(shoppingCartItemDto.getNewYearToyId()));
        if (!isPresentSameShoppingCartItemInDb) {
            ShoppingCartItem shoppingCartItemToSave = shoppingCartItemMapper.dtoToEntity(
                    shoppingCartItemDto,
                    new ShoppingCartItem(),
                    newYearToyRepository,
                    shoppingCartRepository);
            shoppingCartItemRepository.save(shoppingCartItemToSave);
        } else {
            throw new EntityUniqueException("The shopping cart item exists in the database");
        }

    }


    @Override
    public ShoppingCartItemDto getShoppingCartItem(Long id) {
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The cart item  does not exist"));

        return shoppingCartItemMapper.entityToDto(shoppingCartItem, new ShoppingCartItemDto());

    }

    @Override
    public void updateShoppingCartItem(ShoppingCartItemDto shoppingCartItemDto) {
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(shoppingCartItemDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("The cart item  does not exist"));
        shoppingCartItem.setQuantity(shoppingCartItemDto.getQuantity());
        shoppingCartItemRepository.save(shoppingCartItem);

    }

    @Override
    public void deleteShoppingCartItem(Long id) {
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The cart item  does not exist"));
        shoppingCartItemRepository.delete(shoppingCartItem);

    }
}

package com.simbirsoft.NewYearToyStore.service.implementation;

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

import java.util.Optional;
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
    public Optional<ShoppingCartItemDto> saveShoppingCartItem(ShoppingCartItemDto shoppingCartItemDto) {
        Set<ShoppingCartItem> shoppingCartItems = shoppingCartItemRepository.getShoppingCartItemsByShoppingCartId(shoppingCartItemDto.getShoppingCartId());
        boolean isPresentSameShoppingCartItemInDb = shoppingCartItems.stream()
                .map(ShoppingCartItem::getNewYearToy)
                .map(NewYearToy::getId)
                .anyMatch(x -> x.equals(shoppingCartItemDto.getNewYearToyId()));
        if (!isPresentSameShoppingCartItemInDb) {
            ShoppingCartItem shoppingCartItemToSave = shoppingCartItemMapper.dtoToEntity(shoppingCartItemDto, new ShoppingCartItem(), newYearToyRepository, shoppingCartRepository);
            ShoppingCartItemDto shoppingCartItemDtoFromDb = shoppingCartItemMapper.entityToDto(shoppingCartItemRepository.save(shoppingCartItemToSave), new ShoppingCartItemDto());

            return Optional.of(shoppingCartItemDtoFromDb);
        }
        return Optional.empty();
    }


    @Override
    public Optional<ShoppingCartItemDto> getShoppingCartItem(Long id) {
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no EntityException"));
        ShoppingCartItemDto shoppingCartItemDto =
                shoppingCartItemMapper.entityToDto(shoppingCartItem, new ShoppingCartItemDto());

        return Optional.of(shoppingCartItemDto);

    }

    @Override
    public Optional<ShoppingCartItemDto> updateShoppingCartItem(ShoppingCartItemDto shoppingCartItemDtoForUpdate) {
        Long id = shoppingCartItemDtoForUpdate.getId();

        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository
                .findById(id).orElseThrow(() -> new RuntimeException("no EntityException"));
        shoppingCartItem.setQuantity(shoppingCartItemDtoForUpdate.getQuantity());
        ShoppingCartItemDto shoppingCartItemDtoUpdated =
                shoppingCartItemMapper.entityToDto(shoppingCartItemRepository.save(shoppingCartItem), new ShoppingCartItemDto());

        return Optional.of(shoppingCartItemDtoUpdated);

    }

    @Override
    public boolean deleteShoppingCartItem(Long id) {
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("no EntityException"));
        shoppingCartItemRepository.delete(shoppingCartItem);

        return true;

    }
}

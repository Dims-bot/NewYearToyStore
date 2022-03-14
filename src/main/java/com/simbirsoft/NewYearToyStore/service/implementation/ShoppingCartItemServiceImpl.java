package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.mappers.ShoppingCartItemMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartItemDto;
import com.simbirsoft.NewYearToyStore.models.entity.NewYearToy;
import com.simbirsoft.NewYearToyStore.models.entity.ShoppingCart;
import com.simbirsoft.NewYearToyStore.models.entity.ShoppingCartItem;
import com.simbirsoft.NewYearToyStore.repository.abstracts.NewYearToyRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.ShoppingCartItemRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.ShoppingCartRepository;
import com.simbirsoft.NewYearToyStore.service.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

    private ShoppingCartItemMapper shoppingCartItemMapper;
    private ShoppingCartItemRepository shoppingCartItemRepository;
    private NewYearToyRepository newYearToyRepository;
    private ShoppingCartRepository shoppingCartRepository;

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
            ShoppingCartItem shoppingCartItemToSave = shoppingCartItemMapper.updateShoppingCartItem(shoppingCartItemDto, new ShoppingCartItem(), newYearToyRepository, shoppingCartRepository);
            ShoppingCartItemDto shoppingCartItemDtoFromDb = shoppingCartItemMapper.updateShoppingCartItemDto(shoppingCartItemRepository.save(shoppingCartItemToSave), new ShoppingCartItemDto());

            return Optional.of(shoppingCartItemDtoFromDb);
        }
        return Optional.empty();
    }


    @Override
    public Optional<ShoppingCartItemDto> getShoppingCartItem(Long id) {
        Optional<ShoppingCartItem> shoppingCartItemOptional = shoppingCartItemRepository.findById(id);
        if (shoppingCartItemOptional.isPresent()) {
            ShoppingCartItemDto shoppingCartItemDto = shoppingCartItemMapper.updateShoppingCartItemDto(shoppingCartItemOptional.get(), new ShoppingCartItemDto());
            return Optional.of(shoppingCartItemDto);
        }


        return Optional.empty();
    }

    @Override
    public Optional<ShoppingCartItemDto> updateShoppingCartItem(ShoppingCartItemDto shoppingCartItemDtoForUpdate) {
        Long idShoppingCartItemDto = shoppingCartItemDtoForUpdate.getId();
        if(shoppingCartItemRepository.existsById(idShoppingCartItemDto)) {
            ShoppingCartItem shoppingCartItemToUpdate = shoppingCartItemRepository.getById(idShoppingCartItemDto);
            shoppingCartItemToUpdate.setQuantity(shoppingCartItemDtoForUpdate.getQuantity());
            ShoppingCartItemDto shoppingCartItemDtoUpdated = shoppingCartItemMapper.updateShoppingCartItemDto(shoppingCartItemRepository.save(shoppingCartItemToUpdate), new ShoppingCartItemDto());

            return Optional.of(shoppingCartItemDtoUpdated);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteShoppingCartItem(Long id) {
        if(shoppingCartItemRepository.existsById(id)) {
            shoppingCartItemRepository.deleteById(id);
            return  true;
        }
        return false;
    }
}

package com.simbirsoft.NewYearToyStore.mappers;

import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartItemDto;
import com.simbirsoft.NewYearToyStore.models.entity.NewYearToy;
import com.simbirsoft.NewYearToyStore.models.entity.ShoppingCart;
import com.simbirsoft.NewYearToyStore.models.entity.ShoppingCartItem;
import com.simbirsoft.NewYearToyStore.repository.abstracts.NewYearToyRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.ShoppingCartRepository;
import org.mapstruct.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShoppingCartItemMapper {

    @Mapping(target = "newYearToyId", ignore = true)
    @Mapping(target = "shoppingCartId", ignore = true)
    ShoppingCartItemDto updateShoppingCartItemDto(ShoppingCartItem shoppingCartItem,
                                                  @MappingTarget ShoppingCartItemDto shoppingCartItemDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "newYearToy", ignore = true)
    @Mapping(target = "shoppingCart", ignore = true)
    ShoppingCartItem updateShoppingCartItem(ShoppingCartItemDto shoppingCartItemDto,
                                            @MappingTarget ShoppingCartItem shoppingCartItem,
                                            @Context NewYearToyRepository newYearToyRepository,
                                            @Context ShoppingCartRepository shoppingCartRepository);


    @AfterMapping
    default void afterUpdateShoppingCartItemDto(ShoppingCartItem shoppingCartItem, @MappingTarget ShoppingCartItemDto shoppingCartItemDto){
        shoppingCartItemDto.setShoppingCartId(shoppingCartItem.getShoppingCart() == null ? null : shoppingCartItem.getShoppingCart().getId());
        shoppingCartItemDto.setNewYearToyId(shoppingCartItem.getNewYearToy() == null ? null :shoppingCartItem.getNewYearToy().getId());

    }


    @AfterMapping
    default void afterUpdateShoppingCartItem(ShoppingCartItemDto shoppingCartItemDto,
                                             @MappingTarget ShoppingCartItem shoppingCartItem,
                                             @Context NewYearToyRepository newYearToyRepository,
                                             @Context ShoppingCartRepository shoppingCartRepository) {

        if (shoppingCartItemDto.getNewYearToyId() != null && (shoppingCartItem.getNewYearToy() == null || !shoppingCartItem.getNewYearToy().getId().equals(shoppingCartItemDto.getNewYearToyId()))) {
            final NewYearToy newYearToy = newYearToyRepository.findById(shoppingCartItemDto.getNewYearToyId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "newYearToy not found"));
            shoppingCartItem.setNewYearToy(newYearToy);
        }
        if (shoppingCartItemDto.getShoppingCartId() != null && (shoppingCartItem.getShoppingCart() == null || !shoppingCartItem.getShoppingCart().getId().equals(shoppingCartItemDto.getShoppingCartId()))) {
            final ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartItemDto.getShoppingCartId()).
                    orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "shoppingCart not found"));
            shoppingCartItem.setShoppingCart(shoppingCart);
        }

    }

}

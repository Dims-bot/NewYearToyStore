package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDto;
import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartItemDto;

import java.util.Optional;

public interface ShoppingCartItemService {
    Optional<ShoppingCartItemDto> saveShoppingCartItem(ShoppingCartItemDto shoppingCartItemDto);

    Optional<ShoppingCartItemDto> getShoppingCartItem(Long id);

    Optional<ShoppingCartItemDto> updateShoppingCartItem(ShoppingCartItemDto shoppingCartItemDto);

    boolean deleteShoppingCartItem(Long id);
}

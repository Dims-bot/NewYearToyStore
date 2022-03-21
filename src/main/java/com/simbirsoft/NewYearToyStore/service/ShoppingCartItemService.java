package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDto;
import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartItemDto;

import java.util.Optional;

public interface ShoppingCartItemService {

    void saveShoppingCartItem(ShoppingCartItemDto shoppingCartItemDto);

    ShoppingCartItemDto getShoppingCartItem(Long id);

    void updateShoppingCartItem(ShoppingCartItemDto shoppingCartItemDto);

    void deleteShoppingCartItem(Long id);
}

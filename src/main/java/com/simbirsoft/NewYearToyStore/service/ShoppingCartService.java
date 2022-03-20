package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.InventoryRecordDto;
import com.simbirsoft.NewYearToyStore.models.dtos.OrderDto;
import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartDto;
import com.simbirsoft.NewYearToyStore.models.entity.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartService {

    Optional<ShoppingCartDto> saveShoppingCart(ShoppingCartDto shoppingCartDto);

    Optional<ShoppingCartDto> getShoppingCartById(Long id);

    boolean deleteShoppingCart(Long shoppingCartId);

    void buy(ShoppingCartDto shoppingCartD);
}

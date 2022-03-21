package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.InventoryRecordDto;
import com.simbirsoft.NewYearToyStore.models.dtos.OrderDto;
import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartDto;
import com.simbirsoft.NewYearToyStore.models.entity.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartService {

    void saveShoppingCart(ShoppingCartDto shoppingCartDto);

    ShoppingCartDto getShoppingCartById(Long id);


    void buy(ShoppingCartDto shoppingCartD);
}

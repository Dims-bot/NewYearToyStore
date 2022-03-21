package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.OrderDto;

import java.util.Optional;

public interface OrderService {

    void saveOrder(OrderDto orderDto);

    OrderDto getOrder(Long id);

    void deleteOrder(Long id);
}

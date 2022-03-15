package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.OrderDto;

import java.util.Optional;

public interface OrderService {

    Optional<OrderDto> saveOrder(OrderDto orderDto);

    Optional<OrderDto> getOrder(Long id);

    boolean deleteOrder(Long id);
}

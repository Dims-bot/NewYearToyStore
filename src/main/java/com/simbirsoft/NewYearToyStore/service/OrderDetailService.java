package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDto;
import com.simbirsoft.NewYearToyStore.models.dtos.OrderDetailDto;
import com.simbirsoft.NewYearToyStore.models.entity.OrderDetail;

import java.util.Optional;

public interface OrderDetailService {

    Optional<OrderDetailDto> saveOrderDetail(OrderDetailDto orderDetailDto);

    Optional<OrderDetailDto> getOrderDetail(Long id);

    Optional<OrderDetailDto> updateOrderDetail(OrderDetailDto orderDetailDto);

    void deleteOrderDetail(Long id);
}

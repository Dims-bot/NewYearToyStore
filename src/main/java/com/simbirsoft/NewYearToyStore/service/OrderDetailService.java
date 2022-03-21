package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDto;
import com.simbirsoft.NewYearToyStore.models.dtos.OrderDetailDto;
import com.simbirsoft.NewYearToyStore.models.entity.OrderDetail;

import java.util.Optional;

public interface OrderDetailService {

    void saveOrderDetail(OrderDetailDto orderDetailDto);

    OrderDetailDto getOrderDetail(Long id);

    void updateOrderDetail(OrderDetailDto orderDetailDto);

    void deleteOrderDetail(Long id);
}

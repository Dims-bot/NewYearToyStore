package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.mappers.OrderDetailMapper;
import com.simbirsoft.NewYearToyStore.mappers.OrderDetailMapperImpl;
import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDto;
import com.simbirsoft.NewYearToyStore.models.dtos.OrderDetailDto;
import com.simbirsoft.NewYearToyStore.models.dtos.OrderDto;
import com.simbirsoft.NewYearToyStore.models.entity.NewYearToy;
import com.simbirsoft.NewYearToyStore.models.entity.Order;
import com.simbirsoft.NewYearToyStore.models.entity.OrderDetail;
import com.simbirsoft.NewYearToyStore.repository.abstracts.NewYearToyRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.OrderDetailRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.OrderRepository;
import com.simbirsoft.NewYearToyStore.service.OrderDetailService;
import com.simbirsoft.NewYearToyStore.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private OrderDetailRepository orderDetailRepository;
    private OrderDetailMapper orderDetailMapper;
    private NewYearToyRepository newYearToyRepository;
    private OrderRepository orderRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository,
                                  OrderDetailMapper orderDetailMapper,
                                  NewYearToyRepository newYearToyRepository,
                                  OrderRepository orderRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderDetailMapper = orderDetailMapper;
        this.newYearToyRepository = newYearToyRepository;
        this.orderRepository = orderRepository;
    }

    public boolean isPresentSameOrderDetailInDb(OrderDetailDto orderDetailDto) {
        Set<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailByOrderId(orderDetailDto.getOrderId());

        return orderDetails.stream()
                .map(OrderDetail::getNewYearToy)
                .map(NewYearToy::getId)
                .anyMatch(x -> x.equals(orderDetailDto.getNewYearToyId()));
    }

    @Override
    public Optional<OrderDetailDto> saveOrderDetail(OrderDetailDto orderDetailDto) {
        if (!isPresentSameOrderDetailInDb(orderDetailDto)) {
            OrderDetail orderDetailToSave = orderDetailMapper.updateOrderDetail(orderDetailDto, new OrderDetail(),orderRepository,newYearToyRepository);
            OrderDetailDto orderDetailDtoFromDB = orderDetailMapper.updateOrderDetailDto(orderDetailRepository.save(orderDetailToSave), new OrderDetailDto());

            return Optional.of(orderDetailDtoFromDB);
        }

        return Optional.empty();
    }

    @Override
    public Optional<OrderDetailDto> getOrderDetail(Long id) {
        Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(id);

        if (orderDetailOptional.isPresent()) {
            OrderDetailDto orderDetailDto = orderDetailMapper.updateOrderDetailDto(orderDetailOptional.get(), new OrderDetailDto());

            return Optional.of(orderDetailDto);
        }

        return Optional.empty();
    }

    @Override
    public Optional<OrderDetailDto> updateOrderDetail(OrderDetailDto orderDetailDto) {
        return Optional.empty();
    }

    @Override
    public boolean deleteOrderDetail(Long id) {
        return false;
    }
}
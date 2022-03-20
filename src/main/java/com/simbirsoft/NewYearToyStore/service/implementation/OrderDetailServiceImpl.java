package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.mappers.OrderDetailMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.OrderDetailDto;
import com.simbirsoft.NewYearToyStore.models.entity.NewYearToy;
import com.simbirsoft.NewYearToyStore.models.entity.OrderDetail;
import com.simbirsoft.NewYearToyStore.repository.abstracts.NewYearToyRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.OrderDetailRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.OrderRepository;
import com.simbirsoft.NewYearToyStore.service.OrderDetailService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailServiceImpl implements OrderDetailService {

    OrderDetailRepository orderDetailRepository;
    OrderDetailMapper orderDetailMapper;
    NewYearToyRepository newYearToyRepository;
    OrderRepository orderRepository;

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
            OrderDetail orderDetailToSave = orderDetailMapper.dtoToEntity(orderDetailDto, new OrderDetail(), orderRepository, newYearToyRepository);
            OrderDetailDto orderDetailDtoFromDB = orderDetailMapper.entityToDto(orderDetailRepository.save(orderDetailToSave), new OrderDetailDto());

            return Optional.of(orderDetailDtoFromDB);
        }

        throw new RuntimeException("no EntityException");
    }

    @Override
    public Optional<OrderDetailDto> getOrderDetail(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("no EntityException"));
        OrderDetailDto orderDetailDto = orderDetailMapper.entityToDto(orderDetail, new OrderDetailDto());

        return Optional.of(orderDetailDto);

    }

    @Override
    public Optional<OrderDetailDto> updateOrderDetail(OrderDetailDto orderDetailDto) {
        return Optional.empty();
    }

    @Override
    public void deleteOrderDetail(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no EntityException"));
        orderDetailRepository.delete(orderDetail);

    }
}

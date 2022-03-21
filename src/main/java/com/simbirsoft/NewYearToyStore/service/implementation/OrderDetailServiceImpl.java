package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.exceptions.EntityNotFoundException;
import com.simbirsoft.NewYearToyStore.exceptions.EntityUniqueException;
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
    public void saveOrderDetail(OrderDetailDto orderDetailDto) {
        if (isPresentSameOrderDetailInDb(orderDetailDto)) {
            throw new EntityUniqueException("The order detail exists in the database");
        } else if (!orderRepository.existsById(orderDetailDto.getOrderId())) {
            throw new EntityNotFoundException("The order does not exist");
        } else if (!newYearToyRepository.existsById(orderDetailDto.getNewYearToyId())) {
            throw new EntityNotFoundException("The toy does not exist");
        } else {
            OrderDetail orderDetailToSave = orderDetailMapper.dtoToEntity(orderDetailDto,
                    new OrderDetail(),
                    orderRepository,
                    newYearToyRepository);
            orderDetailRepository.save(orderDetailToSave);

        }
    }

    @Override
    public OrderDetailDto getOrderDetail(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The order detail does not exist"));

        return orderDetailMapper.entityToDto(orderDetail, new OrderDetailDto());

    }

    @Override
    public void updateOrderDetail(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("The order detail does not exist"));
        if (!newYearToyRepository.existsById(orderDetailDto.getNewYearToyId())) {
            throw new EntityNotFoundException("The toy does not exist");
        }
        orderDetail.setNewYearToy(newYearToyRepository.getById(orderDetailDto.getNewYearToyId()));
        orderDetail.setQuantity(orderDetailDto.getQuantity());

        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void deleteOrderDetail(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The order detail does not exist"));
        orderDetailRepository.delete(orderDetail);

    }
}

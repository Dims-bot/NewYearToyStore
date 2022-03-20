package com.simbirsoft.NewYearToyStore.service.implementation;


import com.simbirsoft.NewYearToyStore.mappers.OrderMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.OrderDto;
import com.simbirsoft.NewYearToyStore.models.entity.Order;
import com.simbirsoft.NewYearToyStore.repository.abstracts.CustomerRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.OrderRepository;
import com.simbirsoft.NewYearToyStore.service.OrderService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {

    OrderMapper orderMapper;
    OrderRepository orderRepository;
    CustomerRepository customerRepository;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<OrderDto> saveOrder(OrderDto orderDto) {
        Order orderToSave = orderMapper.dtoToEntity(orderDto, new Order(), customerRepository);
        boolean isPresentOrderInDb = orderRepository.existsByCreated(orderToSave.getCreated());
        if (!isPresentOrderInDb) {
            OrderDto orderDtoFromDb = orderMapper.entityToDto(orderRepository.save(orderToSave), new OrderDto());

            return Optional.of(orderDtoFromDb);
        }
        throw new RuntimeException("UniqEntityException");

    }

    @Override
    public Optional<OrderDto> getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("no EntityException"));
        OrderDto orderDto = orderMapper.entityToDto(order, new OrderDto());

        return Optional.of(orderDto);

    }


    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("no EntityException"));
        orderRepository.delete(order);

    }


}

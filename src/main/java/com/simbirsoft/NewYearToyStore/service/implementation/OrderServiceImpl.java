package com.simbirsoft.NewYearToyStore.service.implementation;


import com.simbirsoft.NewYearToyStore.mappers.OrderMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.OrderDto;
import com.simbirsoft.NewYearToyStore.models.entity.Order;
import com.simbirsoft.NewYearToyStore.repository.abstracts.CustomerRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.OrderRepository;
import com.simbirsoft.NewYearToyStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

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
        return Optional.empty();

    }

    @Override
    public Optional<OrderDto> getOrder(Long id) {
        Optional<Order> orderDtoOptional = orderRepository.findById(id);
        if (orderDtoOptional.isPresent()) {
            OrderDto orderDto = orderMapper.entityToDto(orderDtoOptional.get(), new OrderDto());

            return Optional.of(orderDto);
        }
        return Optional.empty();
    }


    @Override
    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }

        return false;
    }


}

package com.simbirsoft.NewYearToyStore.service.implementation;


import com.simbirsoft.NewYearToyStore.exceptions.EntityNotFoundException;
import com.simbirsoft.NewYearToyStore.exceptions.EntityUniqueException;
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
    public void saveOrder(OrderDto orderDto) {
        if (!customerRepository.existsById(orderDto.getCustomerId())) {
            throw new EntityNotFoundException("The order exists in the database");
        }

        Order orderToSave = orderMapper.dtoToEntity(orderDto, new Order(), customerRepository);
        if (orderRepository.existsByCreated(orderToSave.getCreated())) {
            throw new EntityUniqueException("The order exists in the database");
        } else {
            orderRepository.save(orderToSave);
        }

    }

    @Override
    public OrderDto getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The order does not exist"));

        return orderMapper.entityToDto(order, new OrderDto());

    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The order does not exist"));
        orderRepository.delete(order);

    }


}

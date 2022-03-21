package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.exceptions.ArithmeticBusinessLogicException;
import com.simbirsoft.NewYearToyStore.exceptions.EntityNotFoundException;
import com.simbirsoft.NewYearToyStore.exceptions.EntityUniqueException;
import com.simbirsoft.NewYearToyStore.mappers.ShoppingCartMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartDto;
import com.simbirsoft.NewYearToyStore.models.entity.*;
import com.simbirsoft.NewYearToyStore.repository.abstracts.*;
import com.simbirsoft.NewYearToyStore.service.ShoppingCartService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShoppingCartServiceImpl implements ShoppingCartService {

    ShoppingCartMapper shoppingCartMapper;
    ShoppingCartRepository shoppingCartRepository;
    CustomerRepository customerRepository;
    ShoppingCartItemRepository shoppingCartItemRepository;
    InventoryRecordRepository inventoryRecordRepository;
    OrderRepository orderRepository;
    OrderDetailRepository orderDetailRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartMapper shoppingCartMapper,
                                   ShoppingCartRepository shoppingCartRepository,
                                   CustomerRepository customerRepository,
                                   ShoppingCartItemRepository shoppingCartItemRepository,
                                   InventoryRecordRepository inventoryRecordRepository,
                                   OrderRepository orderRepository,
                                   OrderDetailRepository orderDetailRepository
    ) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerRepository = customerRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.inventoryRecordRepository = inventoryRecordRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public void saveShoppingCart(ShoppingCartDto shoppingCartDto) {
        if (!customerRepository.existsById(shoppingCartDto.getId())) {
            throw new EntityNotFoundException("The customer does not exist");
        }
        if (shoppingCartRepository.existsById(shoppingCartDto.getId())) {
            throw new EntityUniqueException("The shopping cart exists in the database");
        }
        ShoppingCart shoppingCartToSave =
                shoppingCartMapper.dtoToEntity(shoppingCartDto, new ShoppingCart(), customerRepository);

        shoppingCartRepository.save(shoppingCartToSave);

    }


    @Override
    public ShoppingCartDto getShoppingCartById(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The shopping cart does not exist"));

        return shoppingCartMapper.entityToDto(shoppingCart, new ShoppingCartDto());

    }

    @Override
    @Transactional
    public void buy(ShoppingCartDto shoppingCartDto) {
        Set<ShoppingCartItem> shoppingCartItemSet = shoppingCartItemRepository
                .getShoppingCartItemsByShoppingCartId(shoppingCartDto.getId());

        Customer customer = customerRepository.findById(shoppingCartDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("The customer does not exist"));

        Order order = new Order(LocalDateTime.now(), customer);
        orderRepository.save(order);

        for (ShoppingCartItem shoppingCartItem : shoppingCartItemSet) {
            Long newYearToyId = shoppingCartItem.getNewYearToy().getId();

            InventoryRecord inventoryRecord = inventoryRecordRepository.findById(newYearToyId)
                    .orElseThrow(() -> new EntityNotFoundException("The inventory record does not exist"));
            int balanceOfToys = inventoryRecord.getQuantity() - shoppingCartItem.getQuantity();
            if (balanceOfToys >= 0) {

                Integer quantityAfterWrightOff = inventoryRecord.getQuantity() - shoppingCartItem.getQuantity();
                inventoryRecord.setQuantity(quantityAfterWrightOff);
                inventoryRecordRepository.save(inventoryRecord);

                OrderDetail orderDetail = new OrderDetail(shoppingCartItem.getNewYearToy(),
                        shoppingCartItem.getQuantity(),
                        order);
                orderDetailRepository.save(orderDetail);

                shoppingCartItemRepository.delete(shoppingCartItem);
            } else {
                throw new ArithmeticBusinessLogicException("Available to Buy only " + inventoryRecord.getQuantity()
                        + " " + inventoryRecord.getNewYearToy().getName());
            }

        }

    }

}

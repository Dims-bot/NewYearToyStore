package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.mappers.ShoppingCartMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.*;
import com.simbirsoft.NewYearToyStore.models.entity.*;
import com.simbirsoft.NewYearToyStore.repository.abstracts.*;
import com.simbirsoft.NewYearToyStore.service.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShoppingCartServiceImpl implements ShoppingCartService {

    ShoppingCartMapper shoppingCartMapper;
    ShoppingCartRepository shoppingCartRepository;
    CustomerRepository customerRepository;
    ShoppingCartItemRepository shoppingCartItemRepository;
    //    OrderService orderService;
//    InventoryRecordService inventoryRecordService;
//    OrderDetailService orderDetailService;
    InventoryRecordRepository inventoryRecordRepository;
    OrderRepository orderRepository;
    OrderDetailRepository orderDetailRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartMapper shoppingCartMapper,
                                   ShoppingCartRepository shoppingCartRepository,
                                   CustomerRepository customerRepository,
                                   ShoppingCartItemRepository shoppingCartItemRepository,
                                   OrderService orderService,
                                   InventoryRecordService inventoryRecordService,
                                   OrderDetailService orderDetailService,
                                   InventoryRecordRepository inventoryRecordRepository,
                                   OrderRepository orderRepository,
                                   OrderDetailRepository orderDetailRepository
    ) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerRepository = customerRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
//        this.orderService = orderService;
//        this.inventoryRecordService = inventoryRecordService;
//        this.orderDetailService = orderDetailService;
        this.inventoryRecordRepository = inventoryRecordRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public Optional<ShoppingCartDto> saveShoppingCart(ShoppingCartDto shoppingCartDto) {
        ShoppingCart shoppingCartToSave = shoppingCartMapper.dtoToEntity(shoppingCartDto, new ShoppingCart(), customerRepository);
        ShoppingCart shoppingCartNew = shoppingCartRepository.save(shoppingCartToSave);
        ShoppingCartDto newShoppingCartDtoFromDb = shoppingCartMapper.entityToDto(shoppingCartNew, new ShoppingCartDto());

        return Optional.of(newShoppingCartDtoFromDb);
    }


    @Override
    public Optional<ShoppingCartDto> getShoppingCartById(Long id) {

        ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no EntityException"));
        ShoppingCartDto shoppingCartDto = shoppingCartMapper.entityToDto(shoppingCart, new ShoppingCartDto());

        return Optional.of(shoppingCartDto);

    }


    @Override
    public boolean deleteShoppingCart(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(() -> new RuntimeException("no EntityException"));
        shoppingCartRepository.delete(shoppingCart);

        return true;

    }

    @Override
    @Transactional
    public void buy(ShoppingCartDto shoppingCartDto) {
        Set<ShoppingCartItem> shoppingCartItemSet = shoppingCartItemRepository
                .getShoppingCartItemsByShoppingCartId(shoppingCartDto.getId());

        Customer customer = customerRepository.findById(shoppingCartDto.getId())
                .orElseThrow(() -> new RuntimeException("no EntityException"));

        Order order = new Order(LocalDateTime.now(), customer);
        orderRepository.save(order);

        for (ShoppingCartItem shoppingCartItem : shoppingCartItemSet) {
            Long newYearToyId = shoppingCartItem.getNewYearToy().getId();

            InventoryRecord inventoryRecord = inventoryRecordRepository.findById(newYearToyId)
                    .orElseThrow(() -> new RuntimeException("no EntityException"));
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
                throw new RuntimeException("InventoryMinusException. Available to Buy " + inventoryRecord.getQuantity()
                        + " " + inventoryRecord.getNewYearToy().getName());
            }

        }

    }


}

package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.mappers.OrderMapper;
import com.simbirsoft.NewYearToyStore.mappers.ShoppingCartMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.*;
import com.simbirsoft.NewYearToyStore.models.entity.*;
import com.simbirsoft.NewYearToyStore.repository.abstracts.CustomerRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.ShoppingCartItemRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.ShoppingCartRepository;
import com.simbirsoft.NewYearToyStore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartMapper shoppingCartMapper;

    private ShoppingCartRepository shoppingCartRepository;

    private CustomerRepository customerRepository;

    private ShoppingCartItemRepository shoppingCartItemRepository;

    private OrderService orderService;

    private InventoryRecordService inventoryRecordService;

    private OrderDetailService orderDetailService;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartMapper shoppingCartMapper,
                                   ShoppingCartRepository shoppingCartRepository,
                                   CustomerRepository customerRepository,
                                   ShoppingCartItemRepository shoppingCartItemRepository,
                                   OrderService orderService,
                                   InventoryRecordService inventoryRecordService,
                                   OrderDetailService orderDetailService
    ) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerRepository = customerRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.orderService = orderService;
        this.inventoryRecordService = inventoryRecordService;
        this.orderDetailService = orderDetailService;
    }

    @Override
    public Optional<ShoppingCartDto> saveShoppingCart(ShoppingCartDto shoppingCartDto) {
        ShoppingCart shoppingCartToSave = shoppingCartMapper.updateShoppingCart(shoppingCartDto, new ShoppingCart(), customerRepository);
        ShoppingCart shoppingCartNew = shoppingCartRepository.save(shoppingCartToSave);
        ShoppingCartDto newShoppingCartDtoFromDb = shoppingCartMapper.updateShoppingCartDto(shoppingCartNew, new ShoppingCartDto());

        return Optional.of(newShoppingCartDtoFromDb);
    }


    @Override
    public Optional<ShoppingCartDto> getShoppingCartById(Long id) {

        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(id);
        if (shoppingCartOptional.isPresent()) {
            ShoppingCartDto shoppingCartDto = shoppingCartMapper.updateShoppingCartDto(shoppingCartOptional.get(), new ShoppingCartDto());
            return Optional.of(shoppingCartDto);

        }
        return Optional.empty();
    }


    @Override
    public boolean deleteShoppingCart(Long shoppingCartId) {
        if (shoppingCartRepository.existsById(shoppingCartId)) {
            shoppingCartRepository.deleteById(shoppingCartId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<OrderDto> buy(ShoppingCartDto shoppingCartDto) {
        Set<ShoppingCartItem> shoppingCartItemSet = shoppingCartItemRepository.getShoppingCartItemsByShoppingCartId(shoppingCartDto.getId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        OrderDto orderDto = new OrderDto(null, LocalDateTime.now().format(formatter), shoppingCartDto.getId());
        Optional<OrderDto> orderDtoOptionalFromDb = orderService.saveOrder(orderDto);

        for (ShoppingCartItem shoppingCartItem : shoppingCartItemSet) {
            Long newYearToyId = shoppingCartItem.getNewYearToy().getId();
            Optional<InventoryRecordDto> inventoryRecordDtoOptional = inventoryRecordService.getInventoryRecordById(newYearToyId);
            Integer quantityAfterWrightOff = inventoryRecordDtoOptional.get().getQuantity() - shoppingCartItem.getQuantity();
            InventoryRecordDto inventoryRecordDtoToSave = inventoryRecordDtoOptional.get();
            inventoryRecordDtoToSave.setQuantity(quantityAfterWrightOff);
            inventoryRecordService.updateInventoryRecord(inventoryRecordDtoToSave);
            OrderDetailDto orderDetailDto = new OrderDetailDto(null, shoppingCartItem.getQuantity(), orderDtoOptionalFromDb.get().getId(), newYearToyId);
            orderDetailService.saveOrderDetail(orderDetailDto);
        }

        return orderDtoOptionalFromDb;
    }


}

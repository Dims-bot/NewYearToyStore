package com.simbirsoft.NewYearToyStore.controllers;


import com.simbirsoft.NewYearToyStore.models.dtos.OrderDetailDto;
import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartItemDto;
import com.simbirsoft.NewYearToyStore.models.entity.OrderDetail;
import com.simbirsoft.NewYearToyStore.service.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/order_details")
public class OrderDetailController {

    private OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrderDetail(@RequestBody OrderDetailDto orderDetailDto) {
        Optional<OrderDetailDto> orderDetailDtoOptional = orderDetailService.saveOrderDetail(orderDetailDto);

        return orderDetailDtoOptional.isPresent() ?
                ResponseEntity.ok().body(orderDetailDtoOptional) :
                ResponseEntity.badRequest().body("Order detail with order " + orderDetailDto.getOrderId() + " and orderDetailId " + orderDetailDto.getId() + "already in Db" );
    }

    @GetMapping("/{id}/order_detail")
    public ResponseEntity<?> getShoppingCartItem(@PathVariable Long id) {
        Optional<OrderDetailDto> orderDetailDtoOptional = orderDetailService.getOrderDetail(id);

        return orderDetailDtoOptional.isPresent() ?
                ResponseEntity.ok().body(orderDetailDtoOptional) :
                ResponseEntity.badRequest().body("Invalid OrderDetail id: " + id);
    }
}

package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.OrderDto;
import com.simbirsoft.NewYearToyStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderDto orderDto) {
        Optional<OrderDto> orderDtoOptional = orderService.saveOrder(orderDto);

        return orderDtoOptional.isPresent() ?
                ResponseEntity.ok().body(orderDtoOptional):
                ResponseEntity.badRequest().body("Order created " + orderDto.getCreated() + "already in DB");

    }

    @GetMapping("/{id}/order")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        Optional<OrderDto> orderDtoOptional = orderService.getOrder(id);
        return orderDtoOptional.isPresent() ?
                ResponseEntity.ok().body(orderDtoOptional) :
                ResponseEntity.badRequest().body("Invalid Order id: " + id);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean isPresentQAndDeletedOrder = orderService.deleteOrder(id);
        return isPresentQAndDeletedOrder ?
                ResponseEntity.ok().body("Order with id " + id + " was deleted"):
                ResponseEntity.badRequest().body("Invalid Order id: " + id);
    }
}

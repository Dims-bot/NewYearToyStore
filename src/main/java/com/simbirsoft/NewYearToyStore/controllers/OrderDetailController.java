package com.simbirsoft.NewYearToyStore.controllers;


import com.simbirsoft.NewYearToyStore.models.dtos.OrderDetailDto;
import com.simbirsoft.NewYearToyStore.service.OrderDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/order_details")
public class OrderDetailController {

    OrderDetailService orderDetailService;

    @PostMapping("/add")
    public ResponseEntity<?> addOrderDetail(@Valid @RequestBody OrderDetailDto orderDetailDto) {
        orderDetailService.saveOrderDetail(orderDetailDto);

        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(@PathVariable Long id) {

        return ResponseEntity.ok().body(orderDetailService.getOrderDetail(id));

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOrderDetail(@Valid @RequestBody OrderDetailDto orderDetailDto) {
        orderDetailService.updateOrderDetail(orderDetailDto);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        orderDetailService.deleteOrderDetail(id);

        return ResponseEntity.ok().body("Order detail with id " + id + " was deleted");

    }
}

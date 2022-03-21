package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartItemDto;
import com.simbirsoft.NewYearToyStore.service.ShoppingCartItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/shopping_cart_items")
public class ShoppingCartItemController {

    ShoppingCartItemService service;

    @PostMapping("/add")
    public ResponseEntity<?> addShoppingCartItem(@Valid @RequestBody ShoppingCartItemDto shoppingCartItemDto) {
        service.saveShoppingCartItem(shoppingCartItemDto);

        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getShoppingCartItem(@PathVariable Long id) {
        ShoppingCartItemDto shoppingCartItemDto = service.getShoppingCartItem(id);

        return ResponseEntity.ok().body(shoppingCartItemDto);

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateShoppingCartItem(@Valid @RequestBody ShoppingCartItemDto shoppingCartItemDto) {
        service.updateShoppingCartItem(shoppingCartItemDto);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteShoppingCartItem(id);

        return ResponseEntity.ok().body("ShoppingCartItem with id " + id + " was deleted");

    }
}
package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartDto;
import com.simbirsoft.NewYearToyStore.service.ShoppingCartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/shopping_carts")
public class ShoppingCartController {

    ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ResponseEntity<?> addShoppingCart(@Valid @RequestBody ShoppingCartDto shoppingCartDtoNew) {
        shoppingCartService.saveShoppingCart(shoppingCartDtoNew);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestBody ShoppingCartDto shoppingCartDtoToBuy) {
        shoppingCartService.buy(shoppingCartDtoToBuy);

        return ResponseEntity.ok("The purchase is done");

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getShoppingCart(@PathVariable Long id) {
        ShoppingCartDto shoppingCartDto = shoppingCartService.getShoppingCartById(id);

        return ResponseEntity.ok().body(shoppingCartDto);

    }



}

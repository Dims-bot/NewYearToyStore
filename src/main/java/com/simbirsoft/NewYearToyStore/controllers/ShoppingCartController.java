package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.OrderDto;
import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartDto;
import com.simbirsoft.NewYearToyStore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/shopping_carts")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addShoppingCart(@RequestBody ShoppingCartDto shoppingCartDtoNew) {
        Optional<ShoppingCartDto> shoppingCartDtoOptional = shoppingCartService.saveShoppingCart(shoppingCartDtoNew);
        return shoppingCartDtoOptional.isPresent() ?
                ResponseEntity.ok().body(shoppingCartDtoOptional) :
                ResponseEntity.badRequest().body("ShoppingCart with id " + shoppingCartDtoNew.getId() + " is already in the DB");
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestBody ShoppingCartDto shoppingCartDtoToBuy) {
        Optional<OrderDto> orderDtoOptional = shoppingCartService.buy(shoppingCartDtoToBuy);
        return ResponseEntity.ok().body(orderDtoOptional);
    }


    @GetMapping("/{id}/shopping_cart")
    public ResponseEntity<?> getShoppingCart(@PathVariable Long id) {
        Optional<ShoppingCartDto> shoppingCartDtoOptional = shoppingCartService.getShoppingCartById(id);
        return shoppingCartDtoOptional.isPresent() ?
                ResponseEntity.ok().body(shoppingCartDtoOptional) :
                ResponseEntity.badRequest().body("Invalid Shopping Cart id: " + id);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean isPresentShoppingCart = shoppingCartService.deleteShoppingCart(id);
        return isPresentShoppingCart ?
                ResponseEntity.ok().body("ShoppingCart with id " + id + " was deleted") :
                ResponseEntity.badRequest().body("Invalid ShoppingCart id: " + id);
    }


}

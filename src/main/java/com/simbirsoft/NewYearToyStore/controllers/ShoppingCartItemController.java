package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartItemDto;
import com.simbirsoft.NewYearToyStore.service.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/shopping_cart_items")
public class ShoppingCartItemController {

    private ShoppingCartItemService shoppingCartItemService;

    @Autowired
    public ShoppingCartItemController(ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartItemService = shoppingCartItemService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addShoppingCartItem(@RequestBody ShoppingCartItemDto shoppingCartItemDto) {
        Optional<ShoppingCartItemDto> shoppingCartItemDtoOptional = shoppingCartItemService.saveShoppingCartItem(shoppingCartItemDto);
        return shoppingCartItemDtoOptional.isPresent() ?
                ResponseEntity.ok().body(shoppingCartItemDtoOptional) :
                ResponseEntity.badRequest()
                        .body("ShoppingCartItem with shoppingCartId " + shoppingCartItemDto.getShoppingCartId() + " and NewYearToyId " + shoppingCartItemDto.getNewYearToyId() + " already in DB");
    }

    @GetMapping("/{id}/shopping_cart_item")
    public ResponseEntity<?> getShoppingCartItem(@PathVariable Long id) {
        Optional<ShoppingCartItemDto> shoppingCartItemDtoOptional = shoppingCartItemService.getShoppingCartItem(id);
        return shoppingCartItemDtoOptional.isPresent() ?
                ResponseEntity.ok().body(shoppingCartItemDtoOptional) :
                ResponseEntity.badRequest().body("Invalid ShoppingCartItem id: " + id);

    }


    @PutMapping("/update")
    public ResponseEntity<?> updateShoppingCartItem(@RequestBody ShoppingCartItemDto shoppingCartItemDto) {
        Optional<ShoppingCartItemDto> shoppingCartItemDtoOptional = shoppingCartItemService.updateShoppingCartItem(shoppingCartItemDto);
        return shoppingCartItemDtoOptional.isPresent() ?
                ResponseEntity.ok().body(shoppingCartItemDtoOptional) :
                ResponseEntity.badRequest().body("Invalid ShoppingCartItem id: " + shoppingCartItemDto.getId());

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean isPresentAndDeletedShoppingCartItem = shoppingCartItemService.deleteShoppingCartItem(id);
        return isPresentAndDeletedShoppingCartItem ?
                ResponseEntity.ok().body("ShoppingCartItem with id " + id + " was deleted") :
                ResponseEntity.badRequest().body("Invalid InventoryRecord id: " + id);

    }
}
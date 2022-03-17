package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartItemDto;
import com.simbirsoft.NewYearToyStore.service.ShoppingCartItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true )
@RequestMapping("/api/shopping_cart_items")
public class ShoppingCartItemController {

    ShoppingCartItemService service;

    @PostMapping("/add")
    public ResponseEntity<?> addShoppingCartItem(@RequestBody ShoppingCartItemDto shoppingCartItemDto) {
        Optional<ShoppingCartItemDto> shoppingCartItemDtoOptional = service.saveShoppingCartItem(shoppingCartItemDto);
        return shoppingCartItemDtoOptional.isPresent() ?
                ResponseEntity.ok().body(shoppingCartItemDtoOptional) :
                ResponseEntity.status(422)
                        .body("ShoppingCartItem with shoppingCartId " + shoppingCartItemDto.getShoppingCartId()
                                + " and NewYearToyId " + shoppingCartItemDto.getNewYearToyId() + " already in DB");
    }

    @GetMapping("/{id}/shopping_cart_item")
    public ResponseEntity<?> getShoppingCartItem(@PathVariable Long id) {
        Optional<ShoppingCartItemDto> shoppingCartItemDtoOptional = service.getShoppingCartItem(id);
        return shoppingCartItemDtoOptional.isPresent() ?
                ResponseEntity.ok().body(shoppingCartItemDtoOptional) :
                ResponseEntity.status(422).body("Invalid ShoppingCartItem id: " + id);

    }


    @PutMapping("/update")
    public ResponseEntity<?> updateShoppingCartItem(@RequestBody ShoppingCartItemDto shoppingCartItemDto) {
        Optional<ShoppingCartItemDto> shoppingCartItemDtoOptional = service.updateShoppingCartItem(shoppingCartItemDto);
        return shoppingCartItemDtoOptional.isPresent() ?
                ResponseEntity.ok().body(shoppingCartItemDtoOptional) :
                ResponseEntity.status(422).body("Invalid ShoppingCartItem id: " + shoppingCartItemDto.getId());

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean isPresentAndDeletedShoppingCartItem = service.deleteShoppingCartItem(id);
        return isPresentAndDeletedShoppingCartItem ?
                ResponseEntity.ok().body("ShoppingCartItem with id " + id + " was deleted") :
                ResponseEntity.status(422).body("Invalid InventoryRecord id: " + id);

    }
}
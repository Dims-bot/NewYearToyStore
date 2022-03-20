package com.simbirsoft.NewYearToyStore.models.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShoppingCartItemDto {
    Long id;
    Long newYearToyId;
    Long shoppingCartId;
    Integer quantity;

//    public ShoppingCartItemDto() {
//    }
//
//    public ShoppingCartItemDto(Long id, Long newYearToyId, Long shoppingCartId, Integer quantity) {
//        this.id = id;
//        this.newYearToyId = newYearToyId;
//        this.shoppingCartId = shoppingCartId;
//        this.quantity = quantity;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getNewYearToyId() {
//        return newYearToyId;
//    }
//
//    public void setNewYearToyId(Long newYearToyId) {
//        this.newYearToyId = newYearToyId;
//    }
//
//    public Long getShoppingCartId() {
//        return shoppingCartId;
//    }
//
//    public void setShoppingCartId(Long shoppingCartId) {
//        this.shoppingCartId = shoppingCartId;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
}

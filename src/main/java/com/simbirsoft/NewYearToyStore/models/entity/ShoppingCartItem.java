package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "shopping_cart_items")
public class ShoppingCartItem extends BaseDomainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_year_toy_id")
    NewYearToy newYearToy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoppingCart_id")
    ShoppingCart shoppingCart;

    @Column
    Integer quantity;

    public ShoppingCartItem(NewYearToy newYearToy, ShoppingCart shoppingCart, Integer quantity, Long id) {
        this.id = id;
        this.newYearToy = newYearToy;
        this.shoppingCart = shoppingCart;
        this.quantity = quantity;
    }

    public ShoppingCartItem() {
    }

    public NewYearToy getNewYearToy() {
        return newYearToy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNewYearToy(NewYearToy newYearToy) {
        this.newYearToy = newYearToy;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

}

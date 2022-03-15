package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Entity
@FieldDefaults(level = AccessLevel.PROTECTED)
@NoArgsConstructor
@ToString(of = {"newYearToy", "shoppingCart", "quantity", "totalPrice"})
@Getter
@Setter
@Table(name = "shopping_cart_items")
public class ShoppingCartItem extends BaseDomainEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_year_toy_id")
    NewYearToy newYearToy;

    @ManyToOne(fetch = FetchType.LAZY)
    ShoppingCart shoppingCart;

    @Column
    int quantity;

    @Column
    int totalPrice;

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

}

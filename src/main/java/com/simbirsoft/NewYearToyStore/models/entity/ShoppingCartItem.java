package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
//@EqualsAndHashCode(of = {"firstName", "lastName", "email"})
@NoArgsConstructor
@ToString(of = {"newYearToy", "shoppingCart", "quantity", "totalPrice"})
@Getter

@Table(name = "shopping_cart_items")
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_year_toy_id")
    private NewYearToy newYearToy;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;

    @Column(nullable = false, precision = 7)
    private int quantity;

    @Column(nullable = false, precision = 7)
    private int totalPrice;

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCartItem(NewYearToy newYearToy, ShoppingCart shoppingCart, int quantity, int totalPrice) {
        this.newYearToy = newYearToy;
        this.shoppingCart = shoppingCart;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}

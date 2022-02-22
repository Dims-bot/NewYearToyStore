package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
//@EqualsAndHashCode(of = {"firstName", "lastName", "email"})
@NoArgsConstructor
@ToString(of = {"shoppingCartItems", "customer", "shoppingCartTotal"})
@Getter
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    private Long id;

    @OneToMany(
            mappedBy = "shoppingCart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ShoppingCartItem> shoppingCartItems;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Customer customer;

    @Column(nullable = false, precision = 7)
    private BigDecimal shoppingCartTotal;

    public void addShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        shoppingCartItems.add(shoppingCartItem);
        shoppingCartItem.setShoppingCart(this);
    }

    public void removeShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        shoppingCartItems.remove(shoppingCartItem);
        shoppingCartItem.setShoppingCart(null);
    }




}

package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PROTECTED)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    Long id;

    @OneToMany(
            mappedBy = "shoppingCart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<ShoppingCartItem> shoppingCartItems;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    Customer customer;

    @Column
    BigDecimal shoppingCartTotal;

    public void addShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        shoppingCartItems.add(shoppingCartItem);
        shoppingCartItem.setShoppingCart(this);
    }

    public void removeShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        shoppingCartItems.remove(shoppingCartItem);
        shoppingCartItem.setShoppingCart(null);
    }

}

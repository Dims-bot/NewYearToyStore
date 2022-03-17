package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
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

}

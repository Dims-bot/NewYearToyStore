package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;


@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "shopping_cart_items")
public class ShoppingCartItem extends BaseDomainEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_year_toy_id")
    NewYearToy newYearToy;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoppingCart_id")
    ShoppingCart shoppingCart;

    @Column
    @Min(0)
    Integer quantity;

//    public ShoppingCartItem() {
//    }
//
////    public ShoppingCartItem(@NotNull NewYearToy newYearToy, @NotNull ShoppingCart shoppingCart, @Min(0) Integer quantity) {
////        this.newYearToy = newYearToy;
////        this.shoppingCart = shoppingCart;
////        this.quantity = quantity;
////    }
//
//    public ShoppingCartItem(Long id, NewYearToy newYearToy, ShoppingCart shoppingCart, Integer quantity) {
//        super(id);
//        this.newYearToy = newYearToy;
//        this.shoppingCart = shoppingCart;
//        this.quantity = quantity;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public NewYearToy getNewYearToy() {
//        return newYearToy;
//    }
//
//    public void setNewYearToy(NewYearToy newYearToy) {
//        this.newYearToy = newYearToy;
//    }
//
//    public ShoppingCart getShoppingCart() {
//        return shoppingCart;
//    }
//
//    public void setShoppingCart(ShoppingCart shoppingCart) {
//        this.shoppingCart = shoppingCart;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ShoppingCartItem)) return false;
//        ShoppingCartItem that = (ShoppingCartItem) o;
//        return newYearToy.equals(that.newYearToy) &&
//                shoppingCart.equals(that.shoppingCart) &&
//                id.equals(that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id ,newYearToy, shoppingCart);
//    }
}

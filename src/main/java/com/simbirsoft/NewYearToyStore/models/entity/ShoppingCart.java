package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    Customer customer;

//    public ShoppingCart() {
//    }
//
//    public ShoppingCart(Long id, Customer customer) {
//        this.id = id;
//        this.customer = customer;
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
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
}

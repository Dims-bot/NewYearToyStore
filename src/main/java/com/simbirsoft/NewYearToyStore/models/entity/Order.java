package com.simbirsoft.NewYearToyStore.models.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseDomainEntity {

    @Column
    @NotNull
    LocalDateTime created;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    Customer customer;

//    public Order() {
//    }
//
//    public Order(Long id, LocalDateTime created, Customer customer) {
//        super(id);
//        this.created = created;
//        this.customer = customer;
//    }
//
//    public LocalDateTime getCreated() {
//        return created;
//    }
//
//    public void setCreated(LocalDateTime created) {
//        this.created = created;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId(){
//        return id;
//    }
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
}



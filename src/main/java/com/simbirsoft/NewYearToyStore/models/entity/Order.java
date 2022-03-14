package com.simbirsoft.NewYearToyStore.models.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@FieldDefaults(level = AccessLevel.PROTECTED)
//@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseDomainEntity {

    @Column
    LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    Customer customer;

    public Order() {
    }

    public Order(LocalDateTime created, Customer customer, Long id) {
        super(id);
        this.created = created;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}



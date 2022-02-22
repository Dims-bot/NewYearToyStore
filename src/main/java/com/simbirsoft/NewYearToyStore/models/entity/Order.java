package com.simbirsoft.NewYearToyStore.models.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@FieldDefaults(level = AccessLevel.PROTECTED)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseDomainEntity {


    @Column
    LocalDateTime created = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    Customer customer;


}

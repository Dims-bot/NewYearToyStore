package com.simbirsoft.NewYearToyStore.models.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
@Entity
//@EqualsAndHashCode(of = {"firstName", "lastName", "email"})
@NoArgsConstructor
@ToString(of = {"id", "newYearToy", "quantity", "order"})
@Getter
@Table(name = "order_details")
public class OrderDetail {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    NewYearToy newYearToy;

    @Column(nullable = false, precision = 7)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

}

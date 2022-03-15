package com.simbirsoft.NewYearToyStore.models.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@FieldDefaults(level = AccessLevel.PROTECTED)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_details")
public class OrderDetail {

    @Id
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    NewYearToy newYearToy;

    @Column
    int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order order;

}

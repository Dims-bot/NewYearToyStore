package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@NoArgsConstructor

@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "inventory_records")
public class InventoryRecord {

    @Id
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    NewYearToy product;

    @Column
    int quantity;
}

package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(of = "product")
@NoArgsConstructor
@ToString (of = {"id", "product", "quantity"})
@Getter
@Table(name = "inventory_records")
public class InventoryRecord {

    @Id
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    NewYearToy product;

    @Column(nullable = false, precision = 7)
    int quantity;

    public InventoryRecord(NewYearToy product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

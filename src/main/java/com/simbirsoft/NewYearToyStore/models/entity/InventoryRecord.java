package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
//@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "inventory_records")
public class InventoryRecord {

    @Id
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    NewYearToy newYearToy;

    @Column
    Integer quantity;

    public InventoryRecord() {
    }

    public InventoryRecord(Long id, NewYearToy newYearToy, Integer quantity) {
        this.id = id;
        this.newYearToy = newYearToy;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NewYearToy getNewYearToy() {
        return newYearToy;
    }

    public void setNewYearToy(NewYearToy newYearToy) {
        this.newYearToy = newYearToy;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

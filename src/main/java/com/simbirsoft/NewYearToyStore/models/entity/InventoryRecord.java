package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
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
    @Min(0)
    Integer quantity;


}

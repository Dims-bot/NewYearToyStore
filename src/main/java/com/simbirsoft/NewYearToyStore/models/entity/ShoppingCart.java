package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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


}

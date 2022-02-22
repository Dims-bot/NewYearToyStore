package com.simbirsoft.NewYearToyStore.models.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@EqualsAndHashCode(of = {"name", "category", "price"})
@NoArgsConstructor
@ToString (of = {"name", "category", "price"})
@Getter
@Table(name = "toys")
public class NewYearToy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 32)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false, precision = 7,scale = 2)
    private BigDecimal price;


    public NewYearToy(String name, @NonNull Category category, @NonNull BigDecimal price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}

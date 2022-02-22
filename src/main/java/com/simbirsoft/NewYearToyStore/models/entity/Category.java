package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@EqualsAndHashCode(of = "categoryName")
@NoArgsConstructor
@ToString (of = "categoryName")
@Getter
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 32)
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }


}

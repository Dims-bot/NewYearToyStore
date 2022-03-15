package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Entity
@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "categories")
public class Category extends BaseDomainEntity {
    @Column
    String categoryName;

    public Category() {
    }

    public Category(String categoryName, Long id) {
        this.categoryName = categoryName;
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }
}

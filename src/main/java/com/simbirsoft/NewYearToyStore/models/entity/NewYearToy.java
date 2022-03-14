package com.simbirsoft.NewYearToyStore.models.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@FieldDefaults(level = AccessLevel.PROTECTED)
//@NoArgsConstructor
@Getter
@Setter
@Table(name = "new_year_toys")
public class NewYearToy extends BaseDomainEntity {

    @Column
    String nameOfToy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;

    @Column
    BigDecimal price;

    public NewYearToy(String nameOfToy, Category category, BigDecimal price, Long id) {
        this.nameOfToy = nameOfToy;
        this.category = category;
        this.price = price;
        this.id = id;
    }

    public NewYearToy() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfToy() {
        return nameOfToy;
    }

    public void setNameOfToy(String nameOfToy) {
        this.nameOfToy = nameOfToy;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

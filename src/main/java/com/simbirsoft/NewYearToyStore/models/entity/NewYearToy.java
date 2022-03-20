package com.simbirsoft.NewYearToyStore.models.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "new_year_toys")
public class NewYearToy extends BaseDomainEntity {

    @Column
    @NotBlank
    String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;

    @Column
    @Min(0)
    BigDecimal price;

//    public NewYearToy(){
//
//    }
//
//    public NewYearToy(String name, Category category, BigDecimal price) {
//        this.name = name;
//        this.category = category;
//        this.price = price;
//    }
//
//    public NewYearToy(Long id, String name, Category category, BigDecimal price) {
//        super(id);
//        this.name = name;
//        this.category = category;
//        this.price = price;
//    }
//
//    public Long getId(){
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
}

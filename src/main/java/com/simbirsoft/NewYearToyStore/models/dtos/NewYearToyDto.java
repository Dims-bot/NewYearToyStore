package com.simbirsoft.NewYearToyStore.models.dtos;


import com.simbirsoft.NewYearToyStore.models.entity.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
public class NewYearToyDto {
    Long id;
    String nameOfToy;
    Long categoryId;
    BigDecimal price;

    public NewYearToyDto() {
    }

    public NewYearToyDto(Long id, String nameOfToy, Long categoryId, BigDecimal price) {
        this.id = id;
        this.nameOfToy = nameOfToy;
        this.categoryId = categoryId;
        this.price = price;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

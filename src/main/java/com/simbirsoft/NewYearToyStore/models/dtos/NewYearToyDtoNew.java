package com.simbirsoft.NewYearToyStore.models.dtos;

import com.simbirsoft.NewYearToyStore.models.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewYearToyDtoNew {
    String nameOfToy;
    Long categoryId;
    BigDecimal price;

//    public NewYearToyDtoNew() {
//    }
//
//    public NewYearToyDtoNew(String nameOfToy, Long categoryId, BigDecimal price) {
//        this.nameOfToy = nameOfToy;
//        this.categoryId = categoryId;
//        this.price = price;
//    }
//
//    public String getNameOfToy() {
//        return nameOfToy;
//    }
//
//    public void setNameOfToy(String nameOfToy) {
//        this.nameOfToy = nameOfToy;
//    }
//
//    public Long getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Long categoryId) {
//        this.categoryId = categoryId;
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

package com.simbirsoft.NewYearToyStore.models.dtos;


import lombok.*;
import lombok.experimental.FieldDefaults;


import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewYearToyDto {
    Long id;
    String name;
    Long categoryId;
    BigDecimal price;

//    public NewYearToyDto() {
//    }
//
//    public NewYearToyDto(Long id, String name, Long categoryId, BigDecimal price) {
//        this.id = id;
//        this.name = name;
//        this.categoryId = categoryId;
//        this.price = price;
//    }
//
//    public Long getId() {
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

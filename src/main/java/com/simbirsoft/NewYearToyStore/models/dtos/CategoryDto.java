package com.simbirsoft.NewYearToyStore.models.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    Long id;
    String categoryName;

//    public CategoryDto() {
//    }
//
//    public CategoryDto(Long id, String categoryName) {
//        this.id = id;
//        this.categoryName = categoryName;
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
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }
}

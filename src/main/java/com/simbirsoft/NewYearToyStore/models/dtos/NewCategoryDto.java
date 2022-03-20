package com.simbirsoft.NewYearToyStore.models.dtos;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.lang.String;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class NewCategoryDto {

    String categoryName;

    public NewCategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public NewCategoryDto() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

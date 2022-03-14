package com.simbirsoft.NewYearToyStore.models.dtos;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.lang.String;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
public class CategoryDtoNew {
    String categoryName;

    public CategoryDtoNew(String categoryName) {

        this.categoryName = categoryName;
    }

    public CategoryDtoNew() {

    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDto;
import com.simbirsoft.NewYearToyStore.models.dtos.NewCategoryDto;

import java.util.Optional;

public interface CategoryService {

    void saveCategory(NewCategoryDto categoryDtoNew);

    CategoryDto getCategoryByName(String categoryName);

    void updateCategory(CategoryDto categoryDtoForUpdate);

    void deleteCategory(Long id);
}

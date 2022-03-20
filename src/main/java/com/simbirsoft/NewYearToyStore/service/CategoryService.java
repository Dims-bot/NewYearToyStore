package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDto;
import com.simbirsoft.NewYearToyStore.models.dtos.NewCategoryDto;

import java.util.Optional;

public interface CategoryService {

    Optional<CategoryDto> saveCategory(NewCategoryDto categoryDtoNew);

    Optional<CategoryDto> getCategoryByName(String categoryName);

    Optional<CategoryDto> updateCategory(CategoryDto categoryDtoForUpdate);

    void deleteCategory(Long id);
}

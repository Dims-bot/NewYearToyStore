package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDto;
import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDtoNew;

import java.util.Optional;

public interface CategoryService {

    Optional<CategoryDto> saveCategory(CategoryDtoNew categoryDtoNew);

    Optional<CategoryDto> getCategoryByName(String categoryName);

    Optional<CategoryDto> updateCategory(CategoryDto categoryDtoForUpdate);

    boolean deleteCategory(Long categoryId);
}

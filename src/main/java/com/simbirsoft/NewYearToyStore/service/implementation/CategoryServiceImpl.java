package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.mappers.CategoryMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDto;
import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDtoNew;
import com.simbirsoft.NewYearToyStore.models.entity.Category;
import com.simbirsoft.NewYearToyStore.repository.abstracts.CategoryRepository;
import com.simbirsoft.NewYearToyStore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Optional<CategoryDto> saveCategory(CategoryDtoNew categoryDtoNew) {
        if(!categoryRepository.existsByCategoryName(categoryDtoNew.getCategoryName())) {
            Category categoryModel = categoryMapper.categoryDtoNewToCategory(categoryDtoNew);
            CategoryDto categoryDto = categoryMapper.CategoryToDto(categoryRepository.save(categoryModel));

            return Optional.of(categoryDto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<CategoryDto> getCategoryByName(String categoryName) {
        CategoryDto categoryDto = categoryMapper.CategoryToDto(categoryRepository.findByCategoryName(categoryName));

        return Optional.ofNullable(categoryDto);
    }

    @Override
    public Optional<CategoryDto> updateCategory(CategoryDto categoryDtoForUpdate) {

        if(categoryRepository.existsById(categoryDtoForUpdate.getId()) &&
        !categoryRepository.existsByCategoryName(categoryDtoForUpdate.getCategoryName())) {
            Optional<Category> categoryOptionalToUpdate = categoryRepository.findById(categoryDtoForUpdate.getId());
            Category categoryToUpdate = categoryOptionalToUpdate.get();
            categoryToUpdate.setCategoryName(categoryDtoForUpdate.getCategoryName());

            CategoryDto categoryDto = categoryMapper.CategoryToDto(categoryRepository.save(categoryToUpdate));

            return Optional.of(categoryDto);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteCategory(Long categoryId) {
        if(categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            return true;
        }

        return false;
    }
}

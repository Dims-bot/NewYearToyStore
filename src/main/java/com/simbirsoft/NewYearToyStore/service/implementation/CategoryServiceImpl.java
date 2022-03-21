package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.exceptions.EntityNotFoundException;
import com.simbirsoft.NewYearToyStore.exceptions.EntityUniqueException;
import com.simbirsoft.NewYearToyStore.mappers.CategoryMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDto;
import com.simbirsoft.NewYearToyStore.models.dtos.NewCategoryDto;
import com.simbirsoft.NewYearToyStore.models.entity.Category;
import com.simbirsoft.NewYearToyStore.repository.abstracts.CategoryRepository;
import com.simbirsoft.NewYearToyStore.service.CategoryService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void saveCategory(NewCategoryDto categoryDtoNew) {
        if (!categoryRepository.existsByCategoryName(categoryDtoNew.getCategoryName())) {
            Category category = categoryMapper.dtoToEntity(categoryDtoNew);
            categoryRepository.save(category);
        } else {
            throw new EntityUniqueException("The category exists in the database");
        }
    }

    @Override
    public CategoryDto getCategoryByName(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new EntityNotFoundException("The category does not exist"));

        return categoryMapper.entityToDto(category);
    }

    @Override
    public void updateCategory(CategoryDto categoryDtoForUpdate) {

        if (!categoryRepository.existsByCategoryName(categoryDtoForUpdate.getCategoryName())) {
            Category categoryToUpdate = categoryRepository.findById(categoryDtoForUpdate.getId())
                    .orElseThrow(() -> new EntityNotFoundException("The category does not exist"));
            categoryToUpdate.setCategoryName(categoryDtoForUpdate.getCategoryName());
            categoryRepository.save(categoryToUpdate);
        } else {
            throw new EntityUniqueException("The category exists in the database");
        }

    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The category does not exist"));
        categoryRepository.delete(category);

    }
}

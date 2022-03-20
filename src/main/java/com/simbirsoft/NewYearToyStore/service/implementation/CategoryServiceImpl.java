package com.simbirsoft.NewYearToyStore.service.implementation;

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

import java.util.Optional;

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
    public Optional<CategoryDto> saveCategory(NewCategoryDto categoryDtoNew) {
        if (!categoryRepository.existsByCategoryName(categoryDtoNew.getCategoryName())) {
            Category categoryModel = categoryMapper.dtoToEntity(categoryDtoNew);
            CategoryDto categoryDto = categoryMapper.entityToDto(categoryRepository.save(categoryModel));

            return Optional.of(categoryDto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<CategoryDto> getCategoryByName(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName).orElseThrow(()-> new RuntimeException("no EntityException"));
        CategoryDto categoryDto = categoryMapper.entityToDto(category);

        return Optional.of(categoryDto);
    }

    @Override
    public Optional<CategoryDto> updateCategory(CategoryDto categoryDtoForUpdate) {

        if (categoryRepository.existsById(categoryDtoForUpdate.getId()) &&
                !categoryRepository.existsByCategoryName(categoryDtoForUpdate.getCategoryName())) {
            Category categoryToUpdate = categoryRepository.findById(categoryDtoForUpdate.getId())
                    .orElseThrow(()-> new RuntimeException("no EntityException"));
            categoryToUpdate.setCategoryName(categoryDtoForUpdate.getCategoryName());

            CategoryDto categoryDto = categoryMapper.entityToDto(categoryRepository.save(categoryToUpdate));

            return Optional.of(categoryDto);
        }
        return Optional.empty();
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("no EntityException"));
        categoryRepository.delete(category);

    }
}

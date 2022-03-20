package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDto;
import com.simbirsoft.NewYearToyStore.models.dtos.NewCategoryDto;
import com.simbirsoft.NewYearToyStore.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true )
@RequestMapping("/api/categories")
public class CategoryController {

    CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@Valid @RequestBody NewCategoryDto categoryDtoNew) {
        Optional<CategoryDto> categoryDtoOptional = categoryService.saveCategory(categoryDtoNew);
        return categoryDtoOptional.isPresent() ?
                ResponseEntity.ok("A category is created"):
                ResponseEntity.status(422).body("Category " + categoryDtoNew.getCategoryName() + " is already in the database");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);

        return ResponseEntity.ok().body("Category with id " + id + " was deleted");

    }

    @GetMapping("/category/{name}")
    public ResponseEntity<?> getCategoryByName(@PathVariable String name) {
        Optional<CategoryDto> categoryDtoOptional = categoryService.getCategoryByName(name);
        return categoryDtoOptional.isPresent() ?
                ResponseEntity.ok().body(categoryDtoOptional) :
                ResponseEntity.status(422).body("Invalid category name: " + name);

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Optional<CategoryDto> categoryDtoOptional = categoryService.updateCategory(categoryDto);
        return categoryDtoOptional.isPresent() ?
                ResponseEntity.ok().body(categoryDtoOptional) :
                ResponseEntity.status(422).body("Invalid category id: " + categoryDto.getId() + " or new categoryName " + categoryDto.getCategoryName());

    }


}

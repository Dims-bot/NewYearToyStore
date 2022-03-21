package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDto;
import com.simbirsoft.NewYearToyStore.models.dtos.NewCategoryDto;
import com.simbirsoft.NewYearToyStore.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true )
@RequestMapping("/api/categories")
public class CategoryController {

    CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@Valid @RequestBody NewCategoryDto categoryDtoNew) {
        categoryService.saveCategory(categoryDtoNew);

    return ResponseEntity.ok("A category is created");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);

        return ResponseEntity.ok().body("Category with id " + id + " was deleted");

    }

    @GetMapping("/category/{name}")
    public ResponseEntity<?> getCategoryByName(@PathVariable String name) {

        return ResponseEntity.ok().body(categoryService.getCategoryByName(name));

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        categoryService.updateCategory(categoryDto);

        return ResponseEntity.ok().build();

    }


}

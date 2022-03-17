package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDto;
import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDtoNew;
import com.simbirsoft.NewYearToyStore.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true )
@RequestMapping("/api/categories")
public class CategoryController {

    CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDtoNew categoryDtoNew) {
        Optional<CategoryDto> categoryDtoOptional = categoryService.saveCategory(categoryDtoNew);
        return categoryDtoOptional.isPresent() ?
                ResponseEntity.ok().body(categoryDtoOptional) :
                ResponseEntity.status(422).body("Category " + categoryDtoNew.getCategoryName() + " is already in the database");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean isPresentCategory = categoryService.deleteCategory(id);
        return isPresentCategory ?
                ResponseEntity.ok().body("Category with id " + id + " was deleted") :
                ResponseEntity.status(422).body("Invalid category id: " + id);

    }

    @GetMapping("/category/{name}")
    public ResponseEntity<?> getCategoryByName(@PathVariable String name) {
        Optional<CategoryDto> categoryDtoOptional = categoryService.getCategoryByName(name);
        return categoryDtoOptional.isPresent() ?
                ResponseEntity.ok().body(categoryDtoOptional) :
                ResponseEntity.status(422).body("Invalid category name: " + name);

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto) {
        Optional<CategoryDto> categoryDtoOptional = categoryService.updateCategory(categoryDto);
        return categoryDtoOptional.isPresent() ?
                ResponseEntity.ok().body(categoryDtoOptional) :
                ResponseEntity.status(422).body("Invalid category id: " + categoryDto.getId() + " or new categoryName " + categoryDto.getCategoryName());

    }


}

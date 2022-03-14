package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDto;
import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDtoNew;
import com.simbirsoft.NewYearToyStore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
//@Api(value = "CategoriesApi")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //@RequestMapping(value = "api/categories/add", method = RequestMethod.POST, produces = "application/json")
//    @ApiOperation(value = "add Category", response = String.class)
//    @ApiResponses(
//            @ApiResponse(code = 200, message = "Add category", response = CategoryDto.class)
//    )
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDtoNew categoryDtoNew) {

        Optional<CategoryDto> categoryDtoOptional = categoryService.saveCategory(categoryDtoNew);

        return categoryDtoOptional.isPresent() ?
                ResponseEntity.ok().body(categoryDtoOptional) :
                ResponseEntity.badRequest().body("Category " + categoryDtoNew.getCategoryName() + " is already in the database");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean isPresentCategory = categoryService.deleteCategory(id);
        return isPresentCategory ?
                ResponseEntity.ok().body("Category with id " + id + " was deleted") :
                ResponseEntity.badRequest().body("Invalid user id: " + id);

    }

    @GetMapping("/{name}/category")
    public ResponseEntity<?> getCategoryByName(@PathVariable String name) {
        Optional<CategoryDto> categoryDtoOptional = categoryService.getCategoryByName(name);

        return categoryDtoOptional.isPresent() ?
                ResponseEntity.ok().body(categoryDtoOptional) :
                ResponseEntity.badRequest().body("Invalid category name: " + name);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto) {

        Optional<CategoryDto> categoryDtoOptional = categoryService.updateCategory(categoryDto);

        return categoryDtoOptional.isPresent() ?
                ResponseEntity.ok().body(categoryDtoOptional) :
                ResponseEntity.badRequest().body("Invalid category id: " + categoryDto.getId() + " or new categoryName " + categoryDto.getCategoryName());
    }


}

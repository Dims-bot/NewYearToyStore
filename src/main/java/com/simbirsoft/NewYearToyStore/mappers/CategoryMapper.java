package com.simbirsoft.NewYearToyStore.mappers;

import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDto;
import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDtoNew;
import com.simbirsoft.NewYearToyStore.models.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring"
        //, unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryMapper {

    CategoryDto CategoryToDto(Category category);


    Category categoryDtoNewToCategory(CategoryDtoNew categoryDtoNew);
}

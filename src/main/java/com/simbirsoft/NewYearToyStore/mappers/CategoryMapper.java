package com.simbirsoft.NewYearToyStore.mappers;

import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDto;
import com.simbirsoft.NewYearToyStore.models.dtos.NewCategoryDto;
import com.simbirsoft.NewYearToyStore.models.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryDto entityToDto(Category category);

    Category dtoToEntity(NewCategoryDto categoryDtoNew);
}

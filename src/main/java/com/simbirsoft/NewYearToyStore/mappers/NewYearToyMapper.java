package com.simbirsoft.NewYearToyStore.mappers;

import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDto;
import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDtoNew;
import com.simbirsoft.NewYearToyStore.models.entity.Category;
import com.simbirsoft.NewYearToyStore.models.entity.NewYearToy;
import com.simbirsoft.NewYearToyStore.repository.abstracts.CategoryRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.NewYearToyRepository;
import org.mapstruct.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewYearToyMapper {

//    NewYearToyDto NewYearToyToDto(NewYearToy newYearToy);
//
//    NewYearToy newYearToyDtoNewToNewYearToy(NewYearToyDtoNew newYearToyDtoNew);

    @Mapping(target = "categoryId", ignore = true)
    NewYearToyDto updateNewYearToyDto(NewYearToy newYearToy, @MappingTarget NewYearToyDto newYearToyDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    NewYearToy updateNewYearToy(NewYearToyDto newYearToyDto, @MappingTarget NewYearToy newYearToy, @Context CategoryRepository categoryRepository);


    @AfterMapping
    default void afterUpdateNewYearToy(NewYearToy newYearToy, @MappingTarget NewYearToyDto newYearToyDto) {
        newYearToyDto.setCategoryId(newYearToy.getCategory() == null ? null : newYearToy.getCategory().getId());
    }

    @AfterMapping
    default void afterUpdateNewYearToy(NewYearToyDto newYearToyDto,
                                       @MappingTarget NewYearToy newYearToy,
                                       @Context CategoryRepository categoryRepository) {
        if (newYearToyDto.getCategoryId() !=null &&(newYearToy.getCategory() == null || !newYearToy.getCategory().getId().equals(newYearToyDto.getCategoryId()))) {
            final Category category = categoryRepository.findById(newYearToyDto.getCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found"));
            newYearToy.setCategory(category);
        }
    }
}

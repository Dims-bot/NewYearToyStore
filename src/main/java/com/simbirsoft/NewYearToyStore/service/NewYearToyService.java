package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDto;
import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDtoNew;

import java.util.Optional;

public interface NewYearToyService {

    Optional<NewYearToyDto> saveNewYearToy(NewYearToyDto newYearToyDto);

    Optional<NewYearToyDto> getNewYearToy(Long id);

    Optional<NewYearToyDto> updateNewYearToy(NewYearToyDto newYearToyDto);

    boolean deleteNewYearToy(Long id);
}

package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDto;
import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDtoNew;

import java.util.Optional;

public interface NewYearToyService {

    void saveNewYearToy(NewYearToyDto newYearToyDto);

    NewYearToyDto getNewYearToy(Long id);

    void updateNewYearToy(NewYearToyDto newYearToyDto);

     void deleteNewYearToy(Long id);
}

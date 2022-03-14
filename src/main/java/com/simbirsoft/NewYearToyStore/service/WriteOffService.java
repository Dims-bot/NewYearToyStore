package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.OrderDto;
import com.simbirsoft.NewYearToyStore.models.dtos.WriteOffDto;
import com.simbirsoft.NewYearToyStore.models.entity.WriteOff;
import com.simbirsoft.NewYearToyStore.models.entity.WriteOffRecord;

import java.util.Optional;

public interface WriteOffService {
    Optional<WriteOffDto> saveWriteOff(WriteOffDto writeOffDto);

    Optional<WriteOffDto> getWriteOff(Long id);

    boolean deleteWriteOff(Long id);
}

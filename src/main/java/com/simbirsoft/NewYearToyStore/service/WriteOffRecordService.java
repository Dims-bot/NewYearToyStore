package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.OrderDetailDto;
import com.simbirsoft.NewYearToyStore.models.dtos.WriteOffRecordDto;

import java.util.Optional;

public interface WriteOffRecordService {

    Optional<WriteOffRecordDto> saveWriteOffRecord(WriteOffRecordDto writeOffRecordDto);

    Optional<WriteOffRecordDto> getWriteOffRecord(Long id);

    Optional<WriteOffRecordDto> updateWriteOffRecord(WriteOffRecordDto writeOffRecordDto);

    boolean deleteWriteOffRecord(Long id);
}

package com.simbirsoft.NewYearToyStore.mappers;


import com.simbirsoft.NewYearToyStore.models.dtos.WriteOffRecordDto;
import com.simbirsoft.NewYearToyStore.models.entity.NewYearToy;
import com.simbirsoft.NewYearToyStore.models.entity.WriteOff;
import com.simbirsoft.NewYearToyStore.models.entity.WriteOffRecord;
import com.simbirsoft.NewYearToyStore.repository.abstracts.NewYearToyRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.WriteOffRepository;
import org.mapstruct.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WriteOffRecordMapper {

    @Mapping(target = "writeOffId", ignore = true)
    @Mapping(target = "newYearToyId", ignore = true)
    WriteOffRecordDto entityToDto(WriteOffRecord writeOffRecord,
                                  @MappingTarget WriteOffRecordDto writeOffRecordDto);

    @Mapping(target = "newYearToy", ignore = true)
    @Mapping(target = "writeOff", ignore = true)
    @Mapping(target = "id", ignore = true)
    WriteOffRecord dtoToEntity(WriteOffRecordDto writeOffRecordDto,
                               @MappingTarget WriteOffRecord writeOffRecord,
                               @Context WriteOffRepository writeOffRepository,
                               @Context NewYearToyRepository newYearToyRepository);

    @AfterMapping
    default void afterEntityTodDto(WriteOffRecord writeOffRecord, @MappingTarget WriteOffRecordDto writeOffRecordDto) {
        writeOffRecordDto.setWriteOffId(writeOffRecord.getWriteOff() == null ?
                null :
                writeOffRecord.getWriteOff().getId());
        writeOffRecordDto.setNewYearToyId(writeOffRecord.getNewYearToy() == null ?
                null :
                writeOffRecord.getNewYearToy().getId());

    }

    @AfterMapping
    default void afterDtoToEntity(WriteOffRecordDto writeOffRecordDto,
                                  @MappingTarget WriteOffRecord writeOffRecord,
                                  @Context WriteOffRepository writeOffRepository,
                                  @Context NewYearToyRepository newYearToyRepository) {
        if (writeOffRecordDto.getNewYearToyId() != null && (writeOffRecord.getNewYearToy() == null
                || writeOffRecord.getNewYearToy().getId().equals(writeOffRecordDto.getNewYearToyId()))) {
            final NewYearToy newYearToy = newYearToyRepository.findById(writeOffRecordDto.getNewYearToyId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NewYearToy not found"));
            writeOffRecord.setNewYearToy(newYearToy);
        }

        if (writeOffRecordDto.getWriteOffId() != null && (writeOffRecord.getWriteOff() == null
                || writeOffRecord.getWriteOff().getId().equals(writeOffRecordDto.getWriteOffId()))) {
            final WriteOff writeOff = writeOffRepository.findById(writeOffRecordDto.getWriteOffId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "writeOff not found"));
            writeOffRecord.setWriteOff(writeOff);
        }
    }


}

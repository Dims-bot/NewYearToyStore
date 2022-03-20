package com.simbirsoft.NewYearToyStore.mappers;


import com.simbirsoft.NewYearToyStore.models.dtos.InventoryRecordDto;
import com.simbirsoft.NewYearToyStore.models.entity.InventoryRecord;
import com.simbirsoft.NewYearToyStore.models.entity.NewYearToy;
import com.simbirsoft.NewYearToyStore.repository.abstracts.NewYearToyRepository;
import org.mapstruct.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InventoryRecordMapper {

    InventoryRecordDto entityToDto(InventoryRecord inventoryRecord,
                                   @MappingTarget InventoryRecordDto inventoryRecordDto);

    @Mapping(target = "newYearToy", ignore = true)
    InventoryRecord dtoToEntity(InventoryRecordDto inventoryRecordDto,
                                @MappingTarget InventoryRecord inventoryRecord,
                                @Context NewYearToyRepository newYearToyRepository);

    @AfterMapping
    default void afterDtoToEntity(InventoryRecordDto inventoryRecordDto,
                                  @MappingTarget InventoryRecord inventoryRecord,
                                  @Context NewYearToyRepository newYearToyRepository) {
        if (inventoryRecordDto.getId() != null && (inventoryRecord.getNewYearToy() == null || !inventoryRecord.getNewYearToy().getId().equals(inventoryRecordDto.getId()))) {
            final NewYearToy newYearToy = newYearToyRepository.findById(inventoryRecord.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NewYearToy not found"));
            inventoryRecord.setNewYearToy(newYearToy);
        }

    }

}

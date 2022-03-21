package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.InventoryRecordDto;
import com.simbirsoft.NewYearToyStore.models.entity.InventoryRecord;

import java.util.Optional;
import java.util.Set;

public interface InventoryRecordService {

    void saveInventoryRecord(InventoryRecordDto inventoryRecordDto);

    InventoryRecordDto getInventoryRecordById(Long id);

    void updateInventoryRecord(InventoryRecordDto inventoryRecordDtoForUpdate);

    void deleteInventoryRecord(Long inventoryRecordId);

    void addInvoice(Set<InventoryRecordDto> inventoryRecordDtoSet);

}

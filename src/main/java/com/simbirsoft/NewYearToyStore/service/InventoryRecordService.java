package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.InventoryRecordDto;
import com.simbirsoft.NewYearToyStore.models.entity.InventoryRecord;

import java.util.Optional;
import java.util.Set;

public interface InventoryRecordService {

    Optional<InventoryRecordDto> saveInventoryRecord(InventoryRecordDto inventoryRecordDto);

    Optional<InventoryRecordDto> getInventoryRecordById(Long id);

    Optional<InventoryRecordDto> updateInventoryRecord(InventoryRecordDto inventoryRecordDtoForUpdate);

    boolean deleteInventoryRecord(Long inventoryRecordId);

    void addInvoice(Set<InventoryRecordDto> inventoryRecordDtoSet);

    void wrightOff(Set<InventoryRecordDto> inventoryRecordDtoSet);
}

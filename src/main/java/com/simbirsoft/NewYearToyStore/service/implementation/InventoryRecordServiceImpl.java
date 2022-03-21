package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.exceptions.EntityNotFoundException;
import com.simbirsoft.NewYearToyStore.exceptions.EntityUniqueException;
import com.simbirsoft.NewYearToyStore.mappers.InventoryRecordMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.InventoryRecordDto;
import com.simbirsoft.NewYearToyStore.models.entity.InventoryRecord;
import com.simbirsoft.NewYearToyStore.repository.abstracts.InventoryRecordRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.NewYearToyRepository;
import com.simbirsoft.NewYearToyStore.service.InventoryRecordService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryRecordServiceImpl implements InventoryRecordService {

    InventoryRecordMapper inventoryRecordMapper;
    InventoryRecordRepository inventoryRecordRepository;
    NewYearToyRepository newYearToyRepository;

    @Autowired
    public InventoryRecordServiceImpl(InventoryRecordMapper inventoryRecordMapper,
                                      InventoryRecordRepository inventoryRecordRepository,
                                      NewYearToyRepository newYearToyRepository) {
        this.inventoryRecordMapper = inventoryRecordMapper;
        this.inventoryRecordRepository = inventoryRecordRepository;
        this.newYearToyRepository = newYearToyRepository;
    }


    @Override
    public void saveInventoryRecord(InventoryRecordDto newInventoryRecordDto) {
        if (!inventoryRecordRepository.existsById(newInventoryRecordDto.getId())) {
            InventoryRecord inventoryRecord = inventoryRecordMapper.dtoToEntity(
                    newInventoryRecordDto,
                    new InventoryRecord(),
                    newYearToyRepository);
            inventoryRecordRepository.save(inventoryRecord);
        } else {
            throw new EntityUniqueException("The inventory record exists in the database");
        }

    }

    @Override
    public InventoryRecordDto getInventoryRecordById(Long id) {
        InventoryRecord inventoryRecord = inventoryRecordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The Inventory record does not exist"));

        return inventoryRecordMapper.entityToDto(inventoryRecord, new InventoryRecordDto());

    }

    @Override
    public void updateInventoryRecord(InventoryRecordDto inventoryRecordDtoForUpdate) {
        InventoryRecord inventoryRecord = inventoryRecordRepository.findById(inventoryRecordDtoForUpdate.getId())
                .orElseThrow((() -> new EntityNotFoundException("The Inventory record does not exist")));
        inventoryRecord.setQuantity(inventoryRecordDtoForUpdate.getQuantity());
        inventoryRecordRepository.save(inventoryRecord);


    }

    @Override
    public void deleteInventoryRecord(Long id) {
        InventoryRecord inventoryRecord = inventoryRecordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The Inventory record does not exist"));
        inventoryRecordRepository.delete(inventoryRecord);

    }

    @Override
    @Transactional
    public void addInvoice(Set<InventoryRecordDto> inventoryRecordDtoSet) {
        Set<InventoryRecordDto> inventoryRecordDtoPresentsToDB = inventoryRecordDtoSet.stream()
                .peek(x -> newYearToyRepository.findById(x.getId())
                        .orElseThrow(() -> new EntityNotFoundException("The New Year Toy does not exist")))
                .filter(x -> inventoryRecordRepository.existsById(x.getId()))
                .collect(Collectors.toSet());
        if (!inventoryRecordDtoPresentsToDB.isEmpty())
            for (InventoryRecordDto inventoryRecordDto : inventoryRecordDtoPresentsToDB) {
                InventoryRecord inventoryRecordToChange = inventoryRecordRepository.getById(inventoryRecordDto.getId());
                Integer newQuantity = inventoryRecordDto.getQuantity() + inventoryRecordToChange.getQuantity();
                inventoryRecordToChange.setQuantity(newQuantity);
                inventoryRecordRepository.save(inventoryRecordToChange);

            }
        Set<InventoryRecordDto> inventoryRecordDtoNotPresentDb = inventoryRecordDtoSet.stream()
                .filter(x -> !inventoryRecordRepository.existsById(x.getId()))
                .collect(Collectors.toSet());
        if (!inventoryRecordDtoNotPresentDb.isEmpty()) {
            for (InventoryRecordDto inventoryRecordDto : inventoryRecordDtoNotPresentDb) {
                InventoryRecord inventoryRecordToSave =
                        inventoryRecordMapper.dtoToEntity(inventoryRecordDto, new InventoryRecord(), newYearToyRepository);
                inventoryRecordRepository.save(inventoryRecordToSave);
            }
        }
    }

}


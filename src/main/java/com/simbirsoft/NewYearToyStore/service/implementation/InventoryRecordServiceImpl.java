package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.mappers.InventoryRecordMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.InventoryRecordDto;
import com.simbirsoft.NewYearToyStore.models.entity.InventoryRecord;
import com.simbirsoft.NewYearToyStore.repository.abstracts.InventoryRecordRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.NewYearToyRepository;
import com.simbirsoft.NewYearToyStore.service.InventoryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InventoryRecordServiceImpl implements InventoryRecordService {

    private InventoryRecordMapper inventoryRecordMapper;

    private InventoryRecordRepository inventoryRecordRepository;

    private NewYearToyRepository newYearToyRepository;

    @Autowired
    public InventoryRecordServiceImpl(InventoryRecordMapper inventoryRecordMapper, InventoryRecordRepository inventoryRecordRepository, NewYearToyRepository newYearToyRepository) {
        this.inventoryRecordMapper = inventoryRecordMapper;
        this.inventoryRecordRepository = inventoryRecordRepository;
        this.newYearToyRepository = newYearToyRepository;
    }


    @Override
    public Optional<InventoryRecordDto> saveInventoryRecord(InventoryRecordDto newInventoryRecordDto) {
        InventoryRecord inventoryRecordToSave = inventoryRecordMapper.updateInventoryRecord(newInventoryRecordDto, new InventoryRecord(), newYearToyRepository);
        InventoryRecord inventoryRecordNew = inventoryRecordRepository.save(inventoryRecordToSave);
        InventoryRecordDto newInventoryRecordDtoFromDb = inventoryRecordMapper.updateInventoryRecordDto(inventoryRecordNew, new InventoryRecordDto());

        return Optional.of(newInventoryRecordDtoFromDb);
    }

    @Override
    public Optional<InventoryRecordDto> getInventoryRecordById(Long id) {
        Optional<InventoryRecord> inventoryRecordOptional = inventoryRecordRepository.findById(id);
        if (inventoryRecordOptional.isPresent()) {
            InventoryRecordDto inventoryRecordDto = inventoryRecordMapper.updateInventoryRecordDto(inventoryRecordOptional.get(), new InventoryRecordDto());
            return Optional.of(inventoryRecordDto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<InventoryRecordDto> updateInventoryRecord(InventoryRecordDto inventoryRecordDtoForUpdate) {
        Long idInventoryRecordDto = inventoryRecordDtoForUpdate.getId();
        if(inventoryRecordRepository.existsById(idInventoryRecordDto)) {
            InventoryRecord inventoryRecordToUpdate = inventoryRecordRepository.getById(idInventoryRecordDto);
            inventoryRecordToUpdate.setQuantity(inventoryRecordDtoForUpdate.getQuantity());

            InventoryRecordDto inventoryRecordDtoUpdated = inventoryRecordMapper.updateInventoryRecordDto(inventoryRecordRepository.save(inventoryRecordToUpdate), new InventoryRecordDto());

            return Optional.of(inventoryRecordDtoUpdated);
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteInventoryRecord(Long inventoryRecordId) {
        if(inventoryRecordRepository.existsById(inventoryRecordId)) {
            inventoryRecordRepository.deleteById(inventoryRecordId);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void addInvoice(Set<InventoryRecordDto> inventoryRecordDtoSet) {
        Set<InventoryRecordDto> inventoryRecordDtoPresentsToDB = inventoryRecordDtoSet.stream()
                .filter(x -> inventoryRecordRepository.existsById(x.getId()))
                .collect(Collectors.toSet());
        if(!inventoryRecordDtoPresentsToDB.isEmpty())
        for(InventoryRecordDto inventoryRecordDto : inventoryRecordDtoPresentsToDB) {
            InventoryRecord inventoryRecordToChangeQuantity = inventoryRecordRepository.getById(inventoryRecordDto.getId());
            Integer newQuantity = inventoryRecordDto.getQuantity() + inventoryRecordToChangeQuantity.getQuantity();
            inventoryRecordToChangeQuantity.setQuantity(newQuantity);
            inventoryRecordRepository.save(inventoryRecordToChangeQuantity);

        }
        Set<InventoryRecordDto> inventoryRecordDtoNotPresentDb = inventoryRecordDtoSet.stream()
                .filter(x -> !inventoryRecordRepository.existsById(x.getId()))
                .collect(Collectors.toSet());
        if(!inventoryRecordDtoNotPresentDb.isEmpty()) {
            for (InventoryRecordDto inventoryRecordDto : inventoryRecordDtoNotPresentDb) {
                InventoryRecord inventoryRecordToSave = inventoryRecordMapper.updateInventoryRecord(inventoryRecordDto, new InventoryRecord(), newYearToyRepository);
                inventoryRecordRepository.save(inventoryRecordToSave);
            }
        }
    }

    @Override
    @Transactional
    public void wrightOff(Set<InventoryRecordDto> inventoryRecordDtoSet) {
        for(InventoryRecordDto inventoryRecordDto : inventoryRecordDtoSet) {
            if (inventoryRecordDtoSet.contains(inventoryRecordDto)) {
                InventoryRecord inventoryRecordToChange = inventoryRecordRepository.getById(inventoryRecordDto.getId());
                Integer newQuantity = inventoryRecordToChange.getQuantity() - inventoryRecordDto.getQuantity();
                inventoryRecordToChange.setQuantity(newQuantity);
                inventoryRecordRepository.save(inventoryRecordToChange);
            }
        }
    }
}


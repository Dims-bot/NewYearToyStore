package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.mappers.WriteOffRecordMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.OrderDetailDto;
import com.simbirsoft.NewYearToyStore.models.dtos.WriteOffRecordDto;
import com.simbirsoft.NewYearToyStore.models.entity.NewYearToy;
import com.simbirsoft.NewYearToyStore.models.entity.OrderDetail;
import com.simbirsoft.NewYearToyStore.models.entity.WriteOff;
import com.simbirsoft.NewYearToyStore.models.entity.WriteOffRecord;
import com.simbirsoft.NewYearToyStore.repository.abstracts.NewYearToyRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.WriteOffRecordRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.WriteOffRepository;
import com.simbirsoft.NewYearToyStore.service.WriteOffRecordService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class WriteOffRecordServiceImpl implements WriteOffRecordService {

    private WriteOffRecordRepository writeOffRecordRepository;
    private WriteOffRecordMapper writeOffRecordMapper;
    private NewYearToyRepository newYearToyRepository;
    WriteOffRepository writeOffRepository;

    public boolean isPresentSameWriteOffRecordInDb(WriteOffRecordDto writeOffRecordDto) {
        if (writeOffRecordRepository.existsByWriteOffId(writeOffRecordDto.getWriteOffId())) {
            Set<WriteOffRecord> writeOffRecords = writeOffRecordRepository.getWriteOffRecordByWriteOffId(writeOffRecordDto.getWriteOffId());

            return writeOffRecords.stream()
                    .map(WriteOffRecord::getNewYearToy)
                    .map(NewYearToy::getId)
                    .anyMatch(x -> x.equals(writeOffRecordDto.getNewYearToyId()));
        }

        return false;

    }

    @Override
    public Optional<WriteOffRecordDto> saveWriteOffRecord(WriteOffRecordDto writeOffRecordDto) {
        if (!isPresentSameWriteOffRecordInDb(writeOffRecordDto)) {
            WriteOffRecord writeOffRecordToSave = writeOffRecordMapper.updateWriteOffRecord(writeOffRecordDto, new WriteOffRecord(), writeOffRepository, newYearToyRepository);
            WriteOffRecordDto writeOffRecordDtoFromDB = writeOffRecordMapper.updateWriteOfRecordDto(writeOffRecordRepository.save(writeOffRecordToSave), new WriteOffRecordDto());

            return Optional.of(writeOffRecordDtoFromDB);
        }

        return Optional.empty();

    }

    @Override
    public Optional<WriteOffRecordDto> getWriteOffRecord(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<WriteOffRecordDto> updateWriteOffRecord(WriteOffRecordDto writeOffRecordDto) {
        return Optional.empty();
    }

    @Override
    public boolean deleteWriteOffRecord(Long id) {
        return false;
    }
}

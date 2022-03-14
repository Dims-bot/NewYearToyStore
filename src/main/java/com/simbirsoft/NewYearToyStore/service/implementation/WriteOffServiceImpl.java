package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.mappers.WriteOffMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.WriteOffDto;
import com.simbirsoft.NewYearToyStore.models.entity.WriteOff;
import com.simbirsoft.NewYearToyStore.repository.abstracts.WriteOffRepository;
import com.simbirsoft.NewYearToyStore.service.WriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WriteOffServiceImpl implements WriteOffService {

    private WriteOffMapper writeOffMapper;
    private WriteOffRepository writeOffRepository;

    @Autowired
    public WriteOffServiceImpl(WriteOffMapper writeOffMapper, WriteOffRepository writeOffRepository) {
        this.writeOffMapper = writeOffMapper;
        this.writeOffRepository = writeOffRepository;
    }

    @Override
    public Optional<WriteOffDto> saveWriteOff(WriteOffDto writeOffDto) {
        WriteOff writeOffToSave = writeOffMapper.updateWriteOff(writeOffDto, new WriteOff());
        boolean isPresentWriteOff = writeOffRepository.existsByCreated(writeOffToSave.getCreated());
        if (!isPresentWriteOff) {
            WriteOffDto writeOffDtoFromDb = writeOffMapper.updateWriteOffDto(writeOffRepository.save(writeOffToSave), new WriteOffDto());

            return Optional.of(writeOffDtoFromDb);

        }
        return Optional.empty();
    }

    @Override
    public Optional<WriteOffDto> getWriteOff(Long id) {
       Optional<WriteOff> writeOffOptional = writeOffRepository.findById(id);
       if(writeOffOptional.isPresent()) {
           WriteOffDto writeOffDto = writeOffMapper.updateWriteOffDto(writeOffOptional.get(),new WriteOffDto());

           return Optional.of(writeOffDto);
       }
        return Optional.empty();
    }

    @Override
    public boolean deleteWriteOff(Long id) {
        if (writeOffRepository.existsById(id)) {
            writeOffRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

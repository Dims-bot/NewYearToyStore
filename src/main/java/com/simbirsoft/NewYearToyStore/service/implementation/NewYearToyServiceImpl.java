package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.mappers.NewYearToyMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDto;
import com.simbirsoft.NewYearToyStore.models.entity.Category;
import com.simbirsoft.NewYearToyStore.models.entity.NewYearToy;
import com.simbirsoft.NewYearToyStore.repository.abstracts.CategoryRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.NewYearToyRepository;
import com.simbirsoft.NewYearToyStore.service.NewYearToyService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewYearToyServiceImpl implements NewYearToyService {

    NewYearToyMapper newYearToyMapper;
    NewYearToyRepository newYearToyRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public NewYearToyServiceImpl(NewYearToyMapper newYearToyMapper,
                                 NewYearToyRepository newYearToyRepository,
                                 CategoryRepository categoryRepository) {
        this.newYearToyMapper = newYearToyMapper;
        this.newYearToyRepository = newYearToyRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<NewYearToyDto> saveNewYearToy(NewYearToyDto newYearToyDto) {

        NewYearToy newYearToyToSave = newYearToyMapper.dtoToEntity(newYearToyDto, new NewYearToy(), categoryRepository);
        NewYearToyDto newYearToyDtoFromDb =
                newYearToyMapper.entityToDto(newYearToyRepository.save(newYearToyToSave), new NewYearToyDto());

        return Optional.of(newYearToyDtoFromDb);
    }

    @Override
    public Optional<NewYearToyDto> getNewYearToy(Long id) {
        NewYearToy newYearToy = newYearToyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no EntityException"));
        NewYearToyDto newYearToyDto = newYearToyMapper.entityToDto(newYearToy, new NewYearToyDto());

        return Optional.of(newYearToyDto);

    }

    @Override
    public Optional<NewYearToyDto> updateNewYearToy(NewYearToyDto newYearToyDtoNew) {
        Long idNewYearToyDto = newYearToyDtoNew.getId();

        NewYearToy newYearToyToUpdate = newYearToyRepository.findById(idNewYearToyDto)
                .orElseThrow(() -> new RuntimeException("no EntityException"));
        newYearToyToUpdate.setName(newYearToyDtoNew.getName());
        newYearToyToUpdate.setPrice(newYearToyDtoNew.getPrice());

        Category categoryFromDto = categoryRepository.findById(newYearToyDtoNew.getCategoryId()).
                orElseThrow(() -> new RuntimeException("no EntityException"));
        newYearToyToUpdate.setCategory(categoryFromDto);
        NewYearToyDto newYearToyDtoUpdated =
                newYearToyMapper.entityToDto(newYearToyRepository.save(newYearToyToUpdate), new NewYearToyDto());

        return Optional.of(newYearToyDtoUpdated);


    }

    @Override
    public boolean deleteNewYearToy(Long id) {
        if (newYearToyRepository.existsById(id)) {
            newYearToyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

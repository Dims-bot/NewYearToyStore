package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.exceptions.EntityNotFoundException;
import com.simbirsoft.NewYearToyStore.exceptions.EntityUniqueException;
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
import org.springframework.transaction.annotation.Transactional;

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
    public void saveNewYearToy(NewYearToyDto newYearToyDto) {
        if (newYearToyRepository.existsByName(newYearToyDto.getName())) {
            throw new EntityUniqueException("The toy exists in the database");
        } else if (!categoryRepository.existsById(newYearToyDto.getCategoryId())) {
            throw new EntityNotFoundException("The category does not exist");
        } else {
            NewYearToy newYearToy = newYearToyMapper.dtoToEntity(newYearToyDto, new NewYearToy(), categoryRepository);
            newYearToyRepository.save(newYearToy);
        }

    }

    @Override
    public NewYearToyDto getNewYearToy(Long id) {
        NewYearToy newYearToy = newYearToyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The toy does not exist"));

        return newYearToyMapper.entityToDto(newYearToy, new NewYearToyDto());

    }

    @Override
    @Transactional
    public void updateNewYearToy(NewYearToyDto newYearToyDtoNew) {
        NewYearToy newYearToyToUpdate = newYearToyRepository.findById(newYearToyDtoNew.getId())
                .orElseThrow(() -> new EntityNotFoundException("The toy does not exist"));
        if (newYearToyRepository.existsByName(newYearToyDtoNew.getName()) &&
                newYearToyDtoNew.getCategoryId().equals(newYearToyToUpdate.getCategory().getId())) {
            throw new EntityUniqueException("The toy with name "
                    + newYearToyDtoNew.getName() + " and category id "
                    + newYearToyDtoNew.getCategoryId() + " exists in the database");
        }
        newYearToyToUpdate.setName(newYearToyDtoNew.getName());
        newYearToyToUpdate.setPrice(newYearToyDtoNew.getPrice());

        Category categoryFromDto = categoryRepository.findById(newYearToyDtoNew.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("The category does not exist"));
        newYearToyToUpdate.setCategory(categoryFromDto);

        newYearToyRepository.save(newYearToyToUpdate);

    }

    @Override
    public void deleteNewYearToy(Long id) {
        NewYearToy newYearToy = newYearToyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The category does not exist"));
        newYearToyRepository.delete(newYearToy);

    }
}

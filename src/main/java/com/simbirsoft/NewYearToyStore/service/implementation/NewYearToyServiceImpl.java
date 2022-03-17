package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.mappers.NewYearToyMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDto;
import com.simbirsoft.NewYearToyStore.models.entity.Category;
import com.simbirsoft.NewYearToyStore.models.entity.NewYearToy;
import com.simbirsoft.NewYearToyStore.repository.abstracts.CategoryRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.NewYearToyRepository;
import com.simbirsoft.NewYearToyStore.service.NewYearToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewYearToyServiceImpl implements NewYearToyService {

    private NewYearToyMapper newYearToyMapper;
    private NewYearToyRepository newYearToyRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public NewYearToyServiceImpl(NewYearToyMapper newYearToyMapper, NewYearToyRepository newYearToyRepository, CategoryRepository categoryRepository) {
        this.newYearToyMapper = newYearToyMapper;
        this.newYearToyRepository = newYearToyRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<NewYearToyDto> saveNewYearToy(NewYearToyDto newYearToyDto) {

        NewYearToy newYearToyToSave = newYearToyMapper.dtoToEntity(newYearToyDto, new NewYearToy(), categoryRepository);
        NewYearToyDto newYearToyDtoFromDb = newYearToyMapper.entityToDto(newYearToyRepository.save(newYearToyToSave), new NewYearToyDto());

        return Optional.of(newYearToyDtoFromDb);
    }

    @Override
    public Optional<NewYearToyDto> getNewYearToy(Long id) {
        Optional<NewYearToy> newYearToyOptional = newYearToyRepository.findById(id);
        if (newYearToyOptional.isPresent()) {
            NewYearToyDto newYearToyDto = newYearToyMapper.entityToDto(newYearToyOptional.get(), new NewYearToyDto());
            return Optional.of(newYearToyDto);
        }


        return Optional.empty();
    }

    @Override
    public Optional<NewYearToyDto> updateNewYearToy(NewYearToyDto newYearToyDtoNew) {
        Long idNewYearToyDto = newYearToyDtoNew.getId();
        if (newYearToyRepository.existsById(idNewYearToyDto) && categoryRepository.existsById(newYearToyDtoNew.getCategoryId())) {
            NewYearToy newYearToyToUpdate = newYearToyRepository.getById(idNewYearToyDto);
            newYearToyToUpdate.setNameOfToy(newYearToyDtoNew.getNameOfToy());
            newYearToyToUpdate.setPrice(newYearToyDtoNew.getPrice());
            Category categoryFromDto = categoryRepository.getById(newYearToyDtoNew.getCategoryId());
            newYearToyToUpdate.setCategory(categoryFromDto);

            NewYearToyDto newYearToyDtoUpdated = newYearToyMapper.entityToDto(newYearToyRepository.save(newYearToyToUpdate), new NewYearToyDto());

            return Optional.of(newYearToyDtoUpdated);
        }
        return Optional.empty();
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

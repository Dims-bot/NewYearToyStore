package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDto;
import com.simbirsoft.NewYearToyStore.service.NewYearToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/toys")
public class NewYearToyController {

    private NewYearToyService newYearToyService;

    @Autowired
    public NewYearToyController(NewYearToyService newYearToyService) {
        this.newYearToyService = newYearToyService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewYearToy(@RequestBody NewYearToyDto newYearToyDto) {
        Optional<NewYearToyDto> newYearToyDtoOptional = newYearToyService.saveNewYearToy(newYearToyDto);

        return ResponseEntity.ok().body(newYearToyDtoOptional);
    }

    @GetMapping("/{id}/toy")
    public ResponseEntity<?> getNewYearToyById(@PathVariable Long id) {
        Optional<NewYearToyDto> newYearToyDtoOptional = newYearToyService.getNewYearToy(id);
        return newYearToyDtoOptional.isPresent() ?
                ResponseEntity.ok().body(newYearToyDtoOptional) :
                ResponseEntity.badRequest().body("Invalid NewYearToy id: " + id);
    }


    @PutMapping("/update")
    public  ResponseEntity<?> updateNewYearToy(@RequestBody NewYearToyDto newYearToyDto) {
        Optional<NewYearToyDto> newYearToyDtoOptional = newYearToyService.updateNewYearToy(newYearToyDto);
        return newYearToyDtoOptional.isPresent() ?
                ResponseEntity.ok().body(newYearToyDtoOptional):
                ResponseEntity.badRequest().body("Invalid NewYearToy id: " + newYearToyDto.getId() + " or Category Id " + newYearToyDto.getCategoryId());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean isPresentNewYearToy = newYearToyService.deleteNewYearToy(id);
        return isPresentNewYearToy ?
                ResponseEntity.ok().body("NewYearToy with id " + id + " was deleted"):
                ResponseEntity.badRequest().body("Invalid NewYearToy id: " + id);
    }


}

package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.NewYearToyDto;
import com.simbirsoft.NewYearToyStore.service.NewYearToyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true )
@RequestMapping("/api/toys")
public class NewYearToyController {

    NewYearToyService newYearToyService;

    @PostMapping("/add")
    public ResponseEntity<?> addNewYearToy(@Valid @RequestBody NewYearToyDto newYearToyDto) {
        newYearToyService.saveNewYearToy(newYearToyDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNewYearToyById(@PathVariable Long id) {
        NewYearToyDto newYearToyDto = newYearToyService.getNewYearToy(id);

        return ResponseEntity.ok().body(newYearToyDto);

    }

    @PutMapping("/update")
    public  ResponseEntity<?> updateNewYearToy(@Valid @RequestBody NewYearToyDto newYearToyDto) {
        newYearToyService.updateNewYearToy(newYearToyDto);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        newYearToyService.deleteNewYearToy(id);
        return ResponseEntity.ok().body("NewYearToy with id " + id + " was deleted");

    }


}

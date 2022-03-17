package com.simbirsoft.NewYearToyStore.controllers;


import com.simbirsoft.NewYearToyStore.models.dtos.WriteOffDto;
import com.simbirsoft.NewYearToyStore.service.WriteOffService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true )
@RequestMapping("/api/write_offs")
public class WriteOffController {

    WriteOffService writeOffService;

    @PostMapping("/add")
    public ResponseEntity<?> addWriteOff(@RequestBody WriteOffDto writeOffDto) {
        Optional<WriteOffDto> writeOffDtoOptional = writeOffService.saveWriteOff(writeOffDto);
        return writeOffDtoOptional.isPresent() ?
                ResponseEntity.ok().body(writeOffDtoOptional) :
                ResponseEntity.status(422).body("Write-off created " + writeOffDto.getCreated() + "already in DB");
    }

    @GetMapping("/{id}/write_off")
    public ResponseEntity<?> getWriteOff(@PathVariable Long id) {
        Optional<WriteOffDto> writeOffDtoOptional = writeOffService.getWriteOff(id);
        return writeOffDtoOptional.isPresent() ?
                ResponseEntity.ok().body(writeOffDtoOptional) :
                ResponseEntity.status(422).body("Invalid WriteOff id: " + id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWriteOff(@PathVariable Long id) {
        boolean isPresentQAndDeletedOrder = writeOffService.deleteWriteOff(id);
        return isPresentQAndDeletedOrder ?
                ResponseEntity.ok().body("WriteOff with id " + id + " was deleted") :
                ResponseEntity.status(422).body("Invalid WriteOff id: " + id);
    }
}

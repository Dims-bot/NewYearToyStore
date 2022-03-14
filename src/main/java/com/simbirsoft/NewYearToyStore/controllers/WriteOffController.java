package com.simbirsoft.NewYearToyStore.controllers;


import com.simbirsoft.NewYearToyStore.models.dtos.OrderDto;
import com.simbirsoft.NewYearToyStore.models.dtos.WriteOffDto;
import com.simbirsoft.NewYearToyStore.service.WriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/write_offs")
public class WriteOffController {

    private WriteOffService writeOffService;

    @Autowired
    public WriteOffController(WriteOffService writeOffService) {
        this.writeOffService = writeOffService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addWriteOff(@RequestBody WriteOffDto writeOffDto) {
        Optional<WriteOffDto> writeOffDtoOptional = writeOffService.saveWriteOff(writeOffDto);

        return writeOffDtoOptional.isPresent() ?
                ResponseEntity.ok().body(writeOffDtoOptional):
                ResponseEntity.badRequest().body("Write-off created " + writeOffDto.getCreated() + "already in DB");
    }

    @GetMapping("/{id}/write_off")
    public ResponseEntity<?> getWriteOff(@PathVariable Long id) {
        Optional<WriteOffDto> writeOffDtoOptional = writeOffService.getWriteOff(id);

        return writeOffDtoOptional.isPresent() ?
                ResponseEntity.ok().body(writeOffDtoOptional) :
                ResponseEntity.badRequest().body("Invalid WriteOff id: " + id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWriteOff(@PathVariable Long id) {
        boolean isPresentQAndDeletedOrder = writeOffService.deleteWriteOff(id);

        return isPresentQAndDeletedOrder ?
                ResponseEntity.ok().body("WriteOff with id " + id + " was deleted"):
                ResponseEntity.badRequest().body("Invalid WriteOff id: " + id);
    }
}

package com.simbirsoft.NewYearToyStore.controllers;


import com.simbirsoft.NewYearToyStore.models.dtos.InventoryRecordDto;
import com.simbirsoft.NewYearToyStore.service.InventoryRecordService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true )
@RequestMapping("/api/inventories")
public class InventoryRecordController {

    private InventoryRecordService inventoryRecordService;

    @PostMapping("/add")
    public ResponseEntity<?> addInventoryRecord(@RequestBody InventoryRecordDto inventoryRecordDtoNew) {
        Optional<InventoryRecordDto> inventoryRecordDtoOptional = inventoryRecordService.saveInventoryRecord(inventoryRecordDtoNew);

        return inventoryRecordDtoOptional.isPresent() ?
                ResponseEntity.ok().body(inventoryRecordDtoOptional) :
                ResponseEntity.status(422).body("InventoryRecord with id " + inventoryRecordDtoNew.getId() + " is already in the DB");

    }

    @PostMapping("/add_invoice")
    public ResponseEntity<?> addInvoice(@RequestBody Set<InventoryRecordDto> inventoryRecordDtoSet) {
        inventoryRecordService.addInvoice(inventoryRecordDtoSet);

        return ResponseEntity.ok().body("The invoice has been added to the database");
    }

    @PostMapping("/wright_off")
    public ResponseEntity<?> wrightOff(@RequestBody Set<InventoryRecordDto> inventoryRecordDtoSet) {
        inventoryRecordService.wrightOff(inventoryRecordDtoSet);
        return ResponseEntity.ok().body("Write-off of goods was made");
    }


    @GetMapping("/{id}/inventory")
    public ResponseEntity<?> getInventoryRecord(@PathVariable Long id) {
        Optional<InventoryRecordDto> inventoryRecordDtoOptional = inventoryRecordService.getInventoryRecordById(id);
        return inventoryRecordDtoOptional.isPresent() ?
                ResponseEntity.ok().body(inventoryRecordDtoOptional) :
                ResponseEntity.status(422).body("Invalid NewYearToy id: " + id);
    }

    @PutMapping("/update")
    public  ResponseEntity<?> updateInventoryRecord(@RequestBody InventoryRecordDto inventoryRecordDto) {
        Optional<InventoryRecordDto> inventoryRecordDtoOptional = inventoryRecordService.updateInventoryRecord(inventoryRecordDto);
        return inventoryRecordDtoOptional.isPresent() ?
                ResponseEntity.ok().body(inventoryRecordDtoOptional):
                ResponseEntity.status(422).body("Invalid NewYearToy id: " + inventoryRecordDto.getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean isPresentInventoryRecord = inventoryRecordService.deleteInventoryRecord(id);
        return isPresentInventoryRecord ?
                ResponseEntity.ok().body("InventoryRecord with id " + id + " was deleted"):
                ResponseEntity.status(422).body("Invalid InventoryRecord id: " + id);
    }


}

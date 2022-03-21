package com.simbirsoft.NewYearToyStore.controllers;


import com.simbirsoft.NewYearToyStore.models.dtos.InventoryRecordDto;
import com.simbirsoft.NewYearToyStore.service.InventoryRecordService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/inventories")
public class InventoryRecordController {

    private InventoryRecordService inventoryRecordService;

    @PostMapping("/add")
    public ResponseEntity<?> addInventoryRecord(@RequestBody InventoryRecordDto inventoryRecordDtoNew) {
        inventoryRecordService.saveInventoryRecord(inventoryRecordDtoNew);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/add_invoice")
    public ResponseEntity<?> addInvoice(@RequestBody Set<InventoryRecordDto> inventoryRecordDtoSet) {
        inventoryRecordService.addInvoice(inventoryRecordDtoSet);

        return ResponseEntity.ok().body("The invoice has been added to the database");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInventoryRecord(@PathVariable Long id) {
        InventoryRecordDto inventoryRecordDto = inventoryRecordService.getInventoryRecordById(id);

        return ResponseEntity.ok().body(inventoryRecordDto);

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateInventoryRecord(@RequestBody InventoryRecordDto inventoryRecordDto) {
        inventoryRecordService.updateInventoryRecord(inventoryRecordDto);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        inventoryRecordService.deleteInventoryRecord(id);

        return ResponseEntity.ok().body("InventoryRecord with id " + id + " was deleted");

    }


}

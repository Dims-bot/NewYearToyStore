package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.OrderDetailDto;
import com.simbirsoft.NewYearToyStore.models.dtos.WriteOffRecordDto;
import com.simbirsoft.NewYearToyStore.models.entity.WriteOffRecord;
import com.simbirsoft.NewYearToyStore.service.WriteOffRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/write_off_records")
public class WriteOffRecordController {

    private WriteOffRecordService writeOffRecordService;

    @Autowired
    public WriteOffRecordController(WriteOffRecordService writeOffRecordService) {
        this.writeOffRecordService = writeOffRecordService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addWriteOffRecord(@RequestBody WriteOffRecordDto writeOffRecordDto) {
        Optional<WriteOffRecordDto> writeOffRecordDtoOptional = writeOffRecordService.saveWriteOffRecord(writeOffRecordDto);

        return writeOffRecordDtoOptional.isPresent() ?
                ResponseEntity.ok().body(writeOffRecordDtoOptional) :
                ResponseEntity.badRequest().body("WriteOffRecord with writeOff " + writeOffRecordDto.getWriteOffId() + " and orderDetailId " + writeOffRecordDto.getId() + "already in Db" );
    }
}

package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.OrderDetailDto;
import com.simbirsoft.NewYearToyStore.models.dtos.WriteOffRecordDto;
import com.simbirsoft.NewYearToyStore.models.entity.WriteOffRecord;
import com.simbirsoft.NewYearToyStore.service.WriteOffRecordService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true )
@RequestMapping("/api/write_off_records")
public class WriteOffRecordController {

    WriteOffRecordService service;

    @PostMapping("/add")
    public ResponseEntity<?> addWriteOffRecord(@RequestBody WriteOffRecordDto writeOffRecordDto) {
        Optional<WriteOffRecordDto> writeOffRecordDtoOptional = service.saveWriteOffRecord(writeOffRecordDto);
        return writeOffRecordDtoOptional.isPresent() ?
                ResponseEntity.ok().body(writeOffRecordDtoOptional) :
                ResponseEntity.status(422).body("WriteOffRecord with writeOff "
                        + writeOffRecordDto.getWriteOffId() + " and orderDetailId "
                        + writeOffRecordDto.getId() + "already in Db");
    }
}

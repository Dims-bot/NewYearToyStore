package com.simbirsoft.NewYearToyStore.models.dtos;


import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WriteOffRecordDto {
    Long id;
    Long newYearToyId;
    Long writeOffId;
    Integer quantity;


}

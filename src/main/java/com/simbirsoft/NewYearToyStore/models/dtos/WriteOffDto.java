package com.simbirsoft.NewYearToyStore.models.dtos;


import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WriteOffDto {

    Long id;
    String created;
    Boolean isApproved;


}

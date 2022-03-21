package com.simbirsoft.NewYearToyStore.models.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    Long id;
    String created;
    Long customerId;


}

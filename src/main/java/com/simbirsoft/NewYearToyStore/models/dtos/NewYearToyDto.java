package com.simbirsoft.NewYearToyStore.models.dtos;


import lombok.*;
import lombok.experimental.FieldDefaults;


import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewYearToyDto {
    Long id;
    String name;
    Long categoryId;
    BigDecimal price;

}

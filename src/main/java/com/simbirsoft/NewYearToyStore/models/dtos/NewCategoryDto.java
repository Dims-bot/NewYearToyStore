package com.simbirsoft.NewYearToyStore.models.dtos;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.lang.String;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewCategoryDto {

    String categoryName;

}

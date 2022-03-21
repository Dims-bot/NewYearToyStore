package com.simbirsoft.NewYearToyStore.models.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShoppingCartItemDto {
    Long id;
    Long newYearToyId;
    Long shoppingCartId;
    Integer quantity;


}

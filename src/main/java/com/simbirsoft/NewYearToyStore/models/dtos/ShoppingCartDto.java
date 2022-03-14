package com.simbirsoft.NewYearToyStore.models.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
public class ShoppingCartDto {

    private Long id;

    public ShoppingCartDto() {
    }

    public ShoppingCartDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

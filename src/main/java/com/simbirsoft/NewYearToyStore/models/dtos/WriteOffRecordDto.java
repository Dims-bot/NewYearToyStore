package com.simbirsoft.NewYearToyStore.models.dtos;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
public class WriteOffRecordDto {
    Long id;
    Long newYearToyId;
    Long writeOffId;
    Integer quantity;

    public WriteOffRecordDto() {
    }

    public WriteOffRecordDto(Long id, Long newYearToyId, Long writeOffId, Integer quantity) {
        this.id = id;
        this.newYearToyId = newYearToyId;
        this.writeOffId = writeOffId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNewYearToyId() {
        return newYearToyId;
    }

    public void setNewYearToyId(Long newYearToyId) {
        this.newYearToyId = newYearToyId;
    }

    public Long getWriteOffId() {
        return writeOffId;
    }

    public void setWriteOffId(Long writeOffId) {
        this.writeOffId = writeOffId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

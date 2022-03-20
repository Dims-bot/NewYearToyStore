package com.simbirsoft.NewYearToyStore.models.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDto {
    Long id;
    Integer quantity;
    Long orderId;
    Long newYearToyId;

//    public OrderDetailDto() {
//    }

//    public OrderDetailDto(Long id, Integer quantity, Long orderId, Long newYearToyId) {
//        this.id = id;
//        this.newYearToyId = newYearToyId;
//        this.quantity = quantity;
//        this.orderId = orderId;
//    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getNewYearToyId() {
//        return newYearToyId;
//    }
//
//    public void setNewYearToyId(Long newYearToyId) {
//        this.newYearToyId = newYearToyId;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public Long getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Long orderId) {
//        this.orderId = orderId;
//    }
}

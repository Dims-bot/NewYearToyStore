package com.simbirsoft.NewYearToyStore.models.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
public class OrderDto {
    Long id;
    String created;
    Long customerId;

    public OrderDto() {
    }

    public OrderDto(Long id, String created, Long customerId) {
        this.id = id;
        this.created = created;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}

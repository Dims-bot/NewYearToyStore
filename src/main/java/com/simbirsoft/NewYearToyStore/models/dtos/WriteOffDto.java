package com.simbirsoft.NewYearToyStore.models.dtos;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
public class WriteOffDto {

    Long id;
    String created;
    Boolean isApproved;

    public WriteOffDto() {
    }

    public WriteOffDto(Long id, String created, Boolean isApproved) {
        this.id = id;
        this.created = created;
        this.isApproved = isApproved;
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

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}

package com.simbirsoft.NewYearToyStore.models.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "write_offs")
public class WriteOff extends BaseDomainEntity {

    @Column
    LocalDateTime created;

    @Column
    Boolean isApproved;

    public WriteOff() {

    }

    public WriteOff(Long id, LocalDateTime created, Boolean isApproved) {
        super(id);
        this.created = created;
        this.isApproved = isApproved;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}


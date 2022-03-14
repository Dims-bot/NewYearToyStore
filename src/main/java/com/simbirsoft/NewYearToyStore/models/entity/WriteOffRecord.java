package com.simbirsoft.NewYearToyStore.models.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@FieldDefaults(level = AccessLevel.PROTECTED)
//@NoArgsConstructor
@Getter
@Setter
@Table(name = "write_off_records")
public class WriteOffRecord extends BaseDomainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_year_toy_id")
    NewYearToy newYearToy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "write_off_id")
    WriteOff writeOff;

    @Column
    Integer quantity;

    public WriteOffRecord() {

    }

    public WriteOffRecord(Long id, NewYearToy newYearToy, WriteOff writeOff, Integer quantity) {
        super(id);
        this.newYearToy = newYearToy;
        this.writeOff = writeOff;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public NewYearToy getNewYearToy() {
        return newYearToy;
    }

    public void setNewYearToy(NewYearToy newYearToy) {
        this.newYearToy = newYearToy;
    }

    public WriteOff getWriteOff() {
        return writeOff;
    }

    public void setWriteOff(WriteOff writeOff) {
        this.writeOff = writeOff;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

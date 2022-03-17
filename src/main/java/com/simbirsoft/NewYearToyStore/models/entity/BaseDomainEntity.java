package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
@Getter
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseDomainEntity {
    public static final String ID_FIELD = "id";
    public static final String VERSION_FIELD = "version";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public BaseDomainEntity() {
    }

    public BaseDomainEntity(Long id) {
        this.id = id;
    }

}

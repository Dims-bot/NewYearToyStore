package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
@Getter


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

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDomainEntity that = (BaseDomainEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

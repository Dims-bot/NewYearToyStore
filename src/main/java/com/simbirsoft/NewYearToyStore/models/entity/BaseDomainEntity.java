package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
@Getter
@FieldDefaults(level = AccessLevel.PROTECTED)
public class BaseDomainEntity {

    public static final String ID_FIELD = "id";
    public static final String VERSION_FIELD = "version";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BaseDomainEntity that = (BaseDomainEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

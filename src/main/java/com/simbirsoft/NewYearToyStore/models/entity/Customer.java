package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "customers")
public class Customer extends BaseDomainEntity {


    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    String email;

}

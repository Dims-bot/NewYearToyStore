package com.simbirsoft.NewYearToyStore.models.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
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

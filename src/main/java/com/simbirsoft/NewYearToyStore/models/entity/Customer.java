package com.simbirsoft.NewYearToyStore.models.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Table(name = "customers")
public class Customer extends BaseDomainEntity {

    @Column
    @NotBlank
    String firstName;

    @Column
    @NotBlank
    String lastName;

    @Column
    @NotBlank
    String email;

    public Customer(){

    }

    public Customer(Long id, String firstName,  String lastName, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Customer(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

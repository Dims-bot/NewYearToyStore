package com.simbirsoft.NewYearToyStore.models.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

@Entity
//@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "customers")
public class Customer extends BaseDomainEntity  {

    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    String email;

    public Customer(){

    }

    public Customer(Long id, String firstName, String lastName, String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {return id;}



}

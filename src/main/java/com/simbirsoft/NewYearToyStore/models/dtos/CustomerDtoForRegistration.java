package com.simbirsoft.NewYearToyStore.models.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
public class CustomerDtoForRegistration {
    String firstName;
    String lastName;
    String email;

    public CustomerDtoForRegistration(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastNameName(String lastNameName) {this.lastName = lastName;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

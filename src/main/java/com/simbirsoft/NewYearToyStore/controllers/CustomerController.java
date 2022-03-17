package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDtoNew;
import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDto;
import com.simbirsoft.NewYearToyStore.models.dtos.RegistrationDto;
import com.simbirsoft.NewYearToyStore.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true )
@RequestMapping("/api/customers")
public class CustomerController {

    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody RegistrationDto registrationDto) {
        Optional<CustomerDto> customerDtoOptional = customerService.saveCustomer(registrationDto);
        return customerDtoOptional.isPresent() ?
                ResponseEntity.ok().body(customerDtoOptional) :
                ResponseEntity.status(422).body("Customer with email " + registrationDto.getEmail() + " is already in the database");

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean isPresentCustomer = customerService.deleteCustomer(id);
        return isPresentCustomer ?
                ResponseEntity.ok().body("Customer with id " + id + " was deleted") :
                ResponseEntity.status(422).body("Invalid customer id: " + id);

    }

    @RequestMapping(value = "/customer/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@PathVariable String email) {
        Optional<CustomerDto> customerDtoOptional = customerService.getCustomerProfile(email);
        return customerDtoOptional.isPresent() ?
                ResponseEntity.ok().body(customerDtoOptional) :
                ResponseEntity.status(422).body("Invalid customer email:" + email);

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customerDto) {
        Optional<CustomerDto> customerDtoOptional = customerService.updateCustomer(customerDto);
        return customerDtoOptional.isPresent() ?
                ResponseEntity.ok().body(customerDtoOptional) :
                ResponseEntity.status(422).body("Invalid customer email:" + customerDto.getEmail());

    }


}

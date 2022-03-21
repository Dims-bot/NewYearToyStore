package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDto;
import com.simbirsoft.NewYearToyStore.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/customers")
public class CustomerController {

    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);

        return ResponseEntity.ok().build();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);

        return ResponseEntity.ok().body("Customer with id " + id + " was deleted");

    }

    @RequestMapping(value = "/customer/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@PathVariable String email) {
        CustomerDto customerDto = customerService.getCustomerProfile(email);

        return ResponseEntity.ok().body(customerDto);

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(customerDto);

        return ResponseEntity.ok().build();

    }

}

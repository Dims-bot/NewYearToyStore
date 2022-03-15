package com.simbirsoft.NewYearToyStore.controllers;

import com.simbirsoft.NewYearToyStore.models.dtos.CategoryDtoNew;
import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDto;
import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDtoForRegistration;
import com.simbirsoft.NewYearToyStore.service.CategoryService;
import com.simbirsoft.NewYearToyStore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService, CategoryService categoryService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDtoForRegistration customerDtoForRegistration) {
        Optional<CustomerDto> customerDtoOptional = customerService.saveCustomer(customerDtoForRegistration);
        return customerDtoOptional.isPresent() ?
                ResponseEntity.ok().body(customerDtoOptional) :
                ResponseEntity.badRequest().body("Customer with email " + customerDtoForRegistration.getEmail() + " is already in the database");

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean isPresentCustomer = customerService.deleteCustomer(id);
        return isPresentCustomer ?
                ResponseEntity.ok().body("Customer with id " + id + " was deleted") :
                ResponseEntity.badRequest().body("Invalid user id: " + id);

    }

    @RequestMapping(value = "/{email}/customer", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@PathVariable String email) {
        Optional<CustomerDto> customerDtoOptional = customerService.getCustomerProfile(email);
        return customerDtoOptional.isPresent() ?
                ResponseEntity.ok().body(customerDtoOptional) :
                ResponseEntity.badRequest().body("Invalid user email:" + email);

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customerDto) {
        Optional<CustomerDto> customerDtoOptional = customerService.updateCustomer(customerDto);
        return customerDtoOptional.isPresent() ?
                ResponseEntity.ok().body(customerDtoOptional) :
                ResponseEntity.badRequest().body("Invalid user email:" + customerDto.getEmail());

    }

    @PostMapping("/toys/add")
    public ResponseEntity<?> addNewYearToy(@RequestBody CategoryDtoNew categoryDtoNew) {
        String name = categoryDtoNew.getCategoryName();
        return ResponseEntity.ok().body(name);
    }


}

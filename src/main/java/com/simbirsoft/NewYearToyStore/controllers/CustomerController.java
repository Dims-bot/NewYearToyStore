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
//@Api(value = "CustomerApi")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService, CategoryService categoryService) {
        this.customerService = customerService;
        //this.categoryService = categoryService;
    }

    //private CategoryService categoryService;

//    @Autowired
//    public CustomerController(CustomerService customerService) {
//        this.customerService = customerService;
//    }


    //@RequestMapping(value = "/api/customers/add", method = RequestMethod.POST, produces = "application/json")
    @PostMapping("/add")
//    @ApiOperation(value = "add Customer", response = String.class)
//    @ApiResponses(
//            @ApiResponse(code = 200, message = "Add customer", response = CustomerDto.class)
//    )
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

//    //@RequestMapping(value = "/categories/add", method = RequestMethod.POST, produces = "application/json")
//    @PostMapping("/api/categories/add")
//    public ResponseEntity<?> addCategory(@RequestBody CategoryDtoNew categoryDtoNew) {
//
//        Optional<CategoryDto> categoryDtoOptional = categoryService.saveCategory(categoryDtoNew);
//
//        return categoryDtoOptional.isPresent() ?
//                ResponseEntity.ok().body(categoryDtoOptional) :
//                ResponseEntity.badRequest().body("Category " + categoryDtoNew.getCategoryName() + " is already in the database");
//
//    }

    @PostMapping("/toys/add")
    public ResponseEntity<?> addNewYearToy(@RequestBody CategoryDtoNew categoryDtoNew) {
        //Optional<NewYearToyDto> newYearToyDtoOptional = newYearToyService.saveNewYearToy(newYearToyDtoNew);
        String name = categoryDtoNew.getCategoryName();
        return ResponseEntity.ok().body(name);
    }



}

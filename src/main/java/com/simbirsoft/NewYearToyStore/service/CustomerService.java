package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDto;
//import com.simbirsoft.NewYearToyStore.models.dtos.RegistrationDto;

import java.util.Optional;

public interface CustomerService {

    void saveCustomer(CustomerDto customerDto);

    CustomerDto getCustomerProfile(String email);

    void updateCustomer(CustomerDto customerDtoForUpdate);

    void deleteCustomer(Long customerId);


}





package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDto;
import com.simbirsoft.NewYearToyStore.models.dtos.RegistrationDto;

import java.util.Optional;

public interface CustomerService {

    Optional<CustomerDto> saveCustomer(RegistrationDto registrationDto);

    Optional<CustomerDto> getCustomerProfile(String email);

    Optional<CustomerDto> updateCustomer(CustomerDto customerDtoForUpdate);

    boolean deleteCustomer(Long customerId);


}





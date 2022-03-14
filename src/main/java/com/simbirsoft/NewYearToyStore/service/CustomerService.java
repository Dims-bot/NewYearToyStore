package com.simbirsoft.NewYearToyStore.service;

import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDto;
import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDtoForRegistration;
import com.simbirsoft.NewYearToyStore.models.entity.Customer;
import liquibase.pro.packaged.C;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface CustomerService {

    Optional<CustomerDto> saveCustomer(CustomerDtoForRegistration customerDtoForRegistration);

    Optional<CustomerDto> getCustomerProfile(String email);

    Optional<CustomerDto> updateCustomer(CustomerDto customerDtoForUpdate);

    boolean deleteCustomer(Long customerId);


}





package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.mappers.CustomerMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDto;
import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDtoForRegistration;
import com.simbirsoft.NewYearToyStore.models.entity.Customer;
import com.simbirsoft.NewYearToyStore.repository.abstracts.CustomerRepository;
import com.simbirsoft.NewYearToyStore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public Optional<CustomerDto> saveCustomer(CustomerDtoForRegistration customerDtoForRegistration) {
        if (!customerRepository.existsByEmail(customerDtoForRegistration.getEmail())) {
            Customer customerModel = customerMapper.customerDtoForRegistrationToCustomer(customerDtoForRegistration);
            CustomerDto customerDto = customerMapper.EntityToDto(customerRepository.save(customerModel));

            return Optional.of(customerDto);
        }

        return Optional.empty();

    }

    @Override
    public boolean deleteCustomer(Long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;

    }

    @Override
    public Optional<CustomerDto> getCustomerProfile(String email) {
        CustomerDto customerDto = customerMapper.EntityToDto(customerRepository.findByEmail(email));

        return Optional.ofNullable(customerDto);

    }

    @Override
    public Optional<CustomerDto> updateCustomer(CustomerDto customerDtoForUpdate) {

        if (customerRepository.existsByEmail(customerDtoForUpdate.getEmail())) {
            Customer customer = customerRepository.findByEmail(customerDtoForUpdate.getEmail());
            customer.setFirstName(customerDtoForUpdate.getFirstName());
            customer.setLastName(customerDtoForUpdate.getLastName());

            CustomerDto customerDto = customerMapper.EntityToDto(customerRepository.save(customer));

            return Optional.of(customerDto);
        }
        return Optional.empty();
    }


}

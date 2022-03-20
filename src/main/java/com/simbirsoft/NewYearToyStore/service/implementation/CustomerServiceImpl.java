package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.mappers.CustomerMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDto;
//import com.simbirsoft.NewYearToyStore.models.dtos.RegistrationDto;
import com.simbirsoft.NewYearToyStore.models.entity.Customer;
import com.simbirsoft.NewYearToyStore.repository.abstracts.CustomerRepository;
import com.simbirsoft.NewYearToyStore.service.CustomerService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository repository;
    CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository, CustomerMapper customerMapper) {
        this.repository = repository;
        this.customerMapper = customerMapper;
    }

    @Override
    public Optional<CustomerDto> saveCustomer(CustomerDto customerDto) {
        if (!repository.existsByEmail(customerDto.getEmail())) {
            Customer customerModel = customerMapper.dtoToEntity(customerDto, new Customer());
            CustomerDto customerDtoFromDb = customerMapper.entityToDto(repository.save(customerModel),new CustomerDto());

            return Optional.of(customerDtoFromDb);
        }

        return Optional.empty();

    }

    @Override
    public boolean deleteCustomer(Long customerId) {
        if (repository.existsById(customerId)) {
            repository.deleteById(customerId);
            return true;
        }
        return false;

    }

    @Override
    public Optional<CustomerDto> getCustomerProfile(String email) {
        Customer customer = repository.findByEmail(email).orElseThrow(() -> new RuntimeException("no EntityException"));
        CustomerDto customerDto = customerMapper.entityToDto(customer, new CustomerDto());

        return Optional.of(customerDto);

    }

    @Override
    public Optional<CustomerDto> updateCustomer(CustomerDto customerDtoForUpdate) {

        if (repository.existsByEmail(customerDtoForUpdate.getEmail())) {
            Customer customer = repository.findByEmail(customerDtoForUpdate.getEmail()).orElseThrow(() -> new RuntimeException("no EntityException"));
            customer.setFirstName(customerDtoForUpdate.getFirstName());
            customer.setLastName(customerDtoForUpdate.getLastName());

            CustomerDto customerDto = customerMapper.entityToDto(repository.save(customer), new CustomerDto());

            return Optional.of(customerDto);
        }
        return Optional.empty();
    }


}

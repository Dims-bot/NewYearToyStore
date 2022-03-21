package com.simbirsoft.NewYearToyStore.service.implementation;

import com.simbirsoft.NewYearToyStore.exceptions.EntityNotFoundException;
import com.simbirsoft.NewYearToyStore.exceptions.EntityUniqueException;
import com.simbirsoft.NewYearToyStore.mappers.CustomerMapper;
import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDto;
import com.simbirsoft.NewYearToyStore.models.entity.Customer;
import com.simbirsoft.NewYearToyStore.repository.abstracts.CustomerRepository;
import com.simbirsoft.NewYearToyStore.service.CustomerService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void saveCustomer(CustomerDto customerDto) {
        if (!repository.existsByEmail(customerDto.getEmail())) {
            Customer customerModel = customerMapper.dtoToEntity(customerDto, new Customer());
            repository.save(customerModel);
        } else {
            throw new EntityUniqueException("The customer exists in the database");
        }

    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The customer does not exist"));
        repository.delete(customer);

    }

    @Override
    public CustomerDto getCustomerProfile(String email) {
        Customer customer = repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("The customer does not exist"));

        return customerMapper.entityToDto(customer, new CustomerDto());

    }

    @Override
    public void updateCustomer(CustomerDto customerDtoForUpdate) {
        Customer customer = repository.findByEmail(customerDtoForUpdate.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("The customer does not exist"));
        customer.setFirstName(customerDtoForUpdate.getFirstName());
        customer.setLastName(customerDtoForUpdate.getLastName());

        repository.save(customer);

    }


}

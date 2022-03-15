package com.simbirsoft.NewYearToyStore.mappers;

import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDto;
import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDtoForRegistration;
import com.simbirsoft.NewYearToyStore.models.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto EntityToDto(Customer customer);

    Customer customerDtoForRegistrationToCustomer(CustomerDtoForRegistration customerDtoForRegistration);

}

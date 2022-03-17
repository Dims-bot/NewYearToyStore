package com.simbirsoft.NewYearToyStore.mappers;

import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDto;
import com.simbirsoft.NewYearToyStore.models.dtos.RegistrationDto;
import com.simbirsoft.NewYearToyStore.models.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto entityToDto(Customer customer);

    Customer dtoToEntity(RegistrationDto registrationDto);

}


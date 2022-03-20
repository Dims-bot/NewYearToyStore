package com.simbirsoft.NewYearToyStore.mappers;

import com.simbirsoft.NewYearToyStore.models.dtos.CustomerDto;

import com.simbirsoft.NewYearToyStore.models.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface CustomerMapper {

    CustomerDto entityToDto(Customer customer, @MappingTarget CustomerDto customerDto);

    @Mapping(target = "id", ignore = true)
    Customer dtoToEntity(CustomerDto customerDto, @MappingTarget Customer customer);

}


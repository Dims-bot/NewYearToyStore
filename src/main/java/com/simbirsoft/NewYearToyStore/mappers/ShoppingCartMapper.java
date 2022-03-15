package com.simbirsoft.NewYearToyStore.mappers;

import com.simbirsoft.NewYearToyStore.models.dtos.ShoppingCartDto;
import com.simbirsoft.NewYearToyStore.models.entity.Customer;
import com.simbirsoft.NewYearToyStore.models.entity.ShoppingCart;
import com.simbirsoft.NewYearToyStore.repository.abstracts.CustomerRepository;
import org.mapstruct.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShoppingCartMapper {

    ShoppingCartDto updateShoppingCartDto(ShoppingCart shoppingCart, @MappingTarget ShoppingCartDto shoppingCartDto);

    @Mapping(target = "customer", ignore = true)
    ShoppingCart updateShoppingCart(ShoppingCartDto shoppingCartDto,
                                    @MappingTarget ShoppingCart shoppingCart,
                                    @Context CustomerRepository customerRepository);

    @AfterMapping
    default void afterUpdateShoppingCart(ShoppingCartDto shoppingCartDto,
                                         @MappingTarget ShoppingCart shoppingCart,
                                         @Context CustomerRepository customerRepository) {
        if (shoppingCartDto.getId() != null && (shoppingCart.getCustomer() == null || !shoppingCart.getCustomer().getId().equals(shoppingCartDto.getId()))) {
            final Customer customer = customerRepository.findById(shoppingCart.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
            shoppingCart.setCustomer(customer);
        }
    }


}

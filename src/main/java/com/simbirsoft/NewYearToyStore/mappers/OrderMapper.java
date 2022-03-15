package com.simbirsoft.NewYearToyStore.mappers;

import com.simbirsoft.NewYearToyStore.models.dtos.OrderDto;
import com.simbirsoft.NewYearToyStore.models.entity.Customer;
import com.simbirsoft.NewYearToyStore.models.entity.Order;
import com.simbirsoft.NewYearToyStore.repository.abstracts.CustomerRepository;
import org.mapstruct.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(source = "created", target = "created", qualifiedByName = "localDateTimeString")
    @Mapping(target = "customerId", ignore = true)
    OrderDto updateOrderDto(Order order, @MappingTarget OrderDto orderDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(source = "created", target = "created", qualifiedByName = "localDateTime")
    Order updateOrder(OrderDto orderDto, @MappingTarget Order order, @Context CustomerRepository customerRepository);

    @Named("localDateTime")
    static LocalDateTime localDateTime(String localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(localDateTime, formatter);
    }

    @AfterMapping
    default void afterOrderDto(Order order, @MappingTarget OrderDto orderDto) {
        orderDto.setCustomerId(order.getCustomer() == null ? null : order.getCustomer().getId());
    }

    @Named("localDateTimeString")
    static String localDateTimeString(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.toString();
    }

    @AfterMapping
    default void afterUpdateOrder(OrderDto orderDto,
                                  @MappingTarget Order order,
                                  @Context CustomerRepository customerRepository) {
        if (orderDto.getCustomerId() != null && (order.getCustomer() == null || !order.getCustomer().getId().equals(orderDto.getCustomerId()))) {
            final Customer customer = customerRepository.findById(orderDto.getCustomerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
            order.setCustomer(customer);
        }

    }


}

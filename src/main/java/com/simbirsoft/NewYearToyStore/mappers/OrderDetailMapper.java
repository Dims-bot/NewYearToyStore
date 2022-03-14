package com.simbirsoft.NewYearToyStore.mappers;

import com.simbirsoft.NewYearToyStore.models.dtos.OrderDetailDto;
import com.simbirsoft.NewYearToyStore.models.entity.NewYearToy;
import com.simbirsoft.NewYearToyStore.models.entity.Order;
import com.simbirsoft.NewYearToyStore.models.entity.OrderDetail;
import com.simbirsoft.NewYearToyStore.repository.abstracts.NewYearToyRepository;
import com.simbirsoft.NewYearToyStore.repository.abstracts.OrderRepository;
import org.mapstruct.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDetailMapper {

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "newYearToyId", ignore = true)
    OrderDetailDto updateOrderDetailDto(OrderDetail orderDetail,
                                        @MappingTarget OrderDetailDto orderDetailDto);

    @Mapping(target = "newYearToy", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "id", ignore = true)
    OrderDetail updateOrderDetail(OrderDetailDto orderDetailDto,
                                  @MappingTarget OrderDetail orderDetail,
                                  @Context OrderRepository orderRepository,
                                  @Context NewYearToyRepository newYearToyRepository);

    @AfterMapping
    default void afterUpdateOrderDetailDto(OrderDetail orderDetail, @MappingTarget OrderDetailDto orderDetailDto){
        orderDetailDto.setOrderId(orderDetail.getOrder() == null ? null : orderDetail.getOrder().getId());
        orderDetailDto.setNewYearToyId(orderDetail.getNewYearToy() == null ? null : orderDetail.getNewYearToy().getId());

    }

    @AfterMapping
    default void  afterUpdateOrderDetail(OrderDetailDto orderDetailDto,
                                         @MappingTarget OrderDetail orderDetail,
                                         @Context OrderRepository orderRepository,
                                         @Context NewYearToyRepository newYearToyRepository) {
        if (orderDetailDto.getNewYearToyId() != null && (orderDetail.getNewYearToy() == null || orderDetail.getNewYearToy().getId().equals(orderDetailDto.getNewYearToyId()))) {
            final NewYearToy newYearToy = newYearToyRepository.findById(orderDetailDto.getNewYearToyId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NewYearToy not found"));
            orderDetail.setNewYearToy(newYearToy);
        }

        if (orderDetailDto.getOrderId() != null && (orderDetail.getOrder() == null || orderDetail.getOrder().getId().equals(orderDetailDto.getOrderId()))) {
            final Order order = orderRepository.findById(orderDetailDto.getOrderId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "order not found"));
            orderDetail.setOrder(order);
        }
    }


}

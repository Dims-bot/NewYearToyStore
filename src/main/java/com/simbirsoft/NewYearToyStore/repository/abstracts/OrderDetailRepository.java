package com.simbirsoft.NewYearToyStore.repository.abstracts;


import com.simbirsoft.NewYearToyStore.models.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.Set;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    boolean existsById(Long id);

    Optional<OrderDetail> findById(Long id);

    Set<OrderDetail> getOrderDetailByOrderId(Long id);


}

package com.simbirsoft.NewYearToyStore.repository.abstracts;


import com.simbirsoft.NewYearToyStore.models.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    boolean existsById(Long id);

    boolean existsByCreated(LocalDateTime localDateTime);

    Optional<Order> findById(Long id);


}

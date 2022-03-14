package com.simbirsoft.NewYearToyStore.repository.abstracts;

import com.simbirsoft.NewYearToyStore.models.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);

    Customer findByEmail(String email);


}

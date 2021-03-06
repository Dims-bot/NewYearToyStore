package com.simbirsoft.NewYearToyStore.repository.abstracts;

import com.simbirsoft.NewYearToyStore.models.entity.NewYearToy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewYearToyRepository extends JpaRepository<NewYearToy, Long> {

    boolean existsById(Long id);

    boolean existsByName(String name);

    Optional<NewYearToy> findById(Long id);

    NewYearToy getById(Long id);




}

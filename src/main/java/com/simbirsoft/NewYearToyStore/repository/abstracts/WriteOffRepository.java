package com.simbirsoft.NewYearToyStore.repository.abstracts;

import com.simbirsoft.NewYearToyStore.models.entity.WriteOff;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.Optional;

public interface WriteOffRepository extends JpaRepository<WriteOff, Long> {
    boolean existsById(Long id);

    boolean existsByCreated(LocalDateTime localDateTime);

    Optional<WriteOff> findById(Long id);
}

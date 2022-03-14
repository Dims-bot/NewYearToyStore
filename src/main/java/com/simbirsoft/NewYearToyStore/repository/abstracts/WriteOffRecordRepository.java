package com.simbirsoft.NewYearToyStore.repository.abstracts;

import com.simbirsoft.NewYearToyStore.models.entity.OrderDetail;
import com.simbirsoft.NewYearToyStore.models.entity.WriteOffRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface WriteOffRecordRepository extends JpaRepository<WriteOffRecord, Long> {

    boolean existsById(Long id);

    boolean existsByWriteOffId(Long id);

    Optional<WriteOffRecord> findById(Long id);

    Set<WriteOffRecord> getWriteOffRecordByWriteOffId(Long id);
}

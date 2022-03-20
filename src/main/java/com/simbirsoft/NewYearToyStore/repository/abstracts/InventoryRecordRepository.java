package com.simbirsoft.NewYearToyStore.repository.abstracts;

import com.simbirsoft.NewYearToyStore.models.entity.InventoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRecordRepository extends JpaRepository<InventoryRecord, Long> {

    boolean existsById(Long id);

    InventoryRecord getById(Long id);

    Optional<InventoryRecord> findById(Long id);



}

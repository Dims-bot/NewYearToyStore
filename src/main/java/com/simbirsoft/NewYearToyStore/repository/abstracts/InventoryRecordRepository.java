package com.simbirsoft.NewYearToyStore.repository.abstracts;

import com.simbirsoft.NewYearToyStore.models.entity.InventoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRecordRepository extends JpaRepository<InventoryRecord, Long> {

    boolean existsById(Long id);


}

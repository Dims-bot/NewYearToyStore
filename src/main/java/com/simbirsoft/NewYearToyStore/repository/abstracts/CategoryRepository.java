package com.simbirsoft.NewYearToyStore.repository.abstracts;

import com.simbirsoft.NewYearToyStore.models.entity.Category;
import com.simbirsoft.NewYearToyStore.models.entity.Customer;
import liquibase.pro.packaged.C;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByCategoryName (String categoryName);

    Category findByCategoryName (String categoryName);

    boolean existsById (Long id);



}

package com.simbirsoft.NewYearToyStore.repository.abstracts;


import com.simbirsoft.NewYearToyStore.models.entity.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.Set;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    boolean existsById(Long id);

    Optional<ShoppingCartItem> findById(Long id);

    Set<ShoppingCartItem> getShoppingCartItemsByShoppingCartId(Long id);

    ShoppingCartItem getById(Long id);
}

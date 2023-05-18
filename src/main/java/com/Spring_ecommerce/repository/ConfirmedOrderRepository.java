package com.Spring_ecommerce.repository;

import com.Spring_ecommerce.model.ConfirmedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmedOrderRepository extends JpaRepository<ConfirmedOrder, Integer> {
    ConfirmedOrder findConfirmedOrderByOrderNumber(Long orderNumber);

}

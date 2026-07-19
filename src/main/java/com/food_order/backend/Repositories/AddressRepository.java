package com.food_order.backend.Repositories;

import com.food_order.backend.enities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

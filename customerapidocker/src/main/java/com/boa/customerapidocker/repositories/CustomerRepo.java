package com.boa.customerapidocker.repositories;

import com.boa.customerapidocker.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
}

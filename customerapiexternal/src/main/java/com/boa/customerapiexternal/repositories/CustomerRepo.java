package com.boa.customerapiexternal.repositories;

import com.boa.customerapiexternal.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
}

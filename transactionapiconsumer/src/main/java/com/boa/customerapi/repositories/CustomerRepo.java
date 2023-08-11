package com.boa.customerapi.repositories;


import com.boa.customerapi.dtos.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo extends MongoRepository<Customer, Long> {
}

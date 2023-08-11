package com.boa.transactionapi.repositories;


import com.boa.transactionapi.dtos.Customer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo extends MongoRepository<Customer, Long> {
}

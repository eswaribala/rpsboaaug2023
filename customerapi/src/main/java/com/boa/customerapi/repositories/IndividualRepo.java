package com.boa.customerapi.repositories;

import com.boa.customerapi.models.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualRepo extends JpaRepository<Individual,Long> {
}

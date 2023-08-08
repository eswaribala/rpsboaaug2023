package com.boa.customerapiexternal.repositories;

import com.boa.customerapiexternal.models.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndividualRepo extends JpaRepository<Individual,Long> {

    @Query("Select i from Individual i where i.contactNo=:contactNo")
    public List<Individual> findByContactNo(@Param("contactNo") long contactNo);


}

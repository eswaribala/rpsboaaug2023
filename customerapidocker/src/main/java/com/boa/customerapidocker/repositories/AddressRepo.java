package com.boa.customerapidocker.repositories;

import com.boa.customerapidocker.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AddressRepo extends JpaRepository<Address,Long> {
    @Query("Select a from Address a where a.customer.customerId=:customerId")
    public List<Address> findByCustomerId(@Param("customerId") long customerId);
}

package com.boa.customerapiexternal.repositories;

import com.boa.customerapiexternal.models.Corporate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateRepo extends JpaRepository<Corporate,Long> {
}

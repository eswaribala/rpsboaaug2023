package com.boa.customerapidocker.repositories;

import com.boa.customerapidocker.models.Corporate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateRepo extends JpaRepository<Corporate,Long> {
}

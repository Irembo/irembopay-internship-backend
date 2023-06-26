package com.irembo.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.irembo.portal.model.Bank;
import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<Bank, UUID> {
    // Custom query methods can be defined here if needed
}

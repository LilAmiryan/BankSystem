package com.example.banksystem.repository;

import com.example.banksystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByClient_clientId(Long id);

}

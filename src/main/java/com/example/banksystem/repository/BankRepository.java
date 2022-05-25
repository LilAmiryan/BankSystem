package com.example.banksystem.repository;

import com.example.banksystem.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}

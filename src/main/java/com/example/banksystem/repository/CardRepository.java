package com.example.banksystem.repository;

import com.example.banksystem.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    public boolean existsByCardNumber(String cardNumber);
    boolean existsByClient_clientId(Long id);
}

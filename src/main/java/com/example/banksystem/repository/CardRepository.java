package com.example.banksystem.repository;

import com.example.banksystem.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    public boolean existsByCardNumber(String cardNumber);
}

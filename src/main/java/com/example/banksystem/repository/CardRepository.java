package com.example.banksystem.repository;

import com.example.banksystem.model.Account;
import com.example.banksystem.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    public boolean existsCardByCardNumber(String cardNumber);
    public Account getAccountByCardNumber(String cardNumber);
    boolean existsByClient_clientId(Long id);

    public Card getCardByCardNumber(String cardNumber);
}

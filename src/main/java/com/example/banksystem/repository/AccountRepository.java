package com.example.banksystem.repository;

import com.example.banksystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsAccountByAccountNumber(String accountNumber);
    boolean existsAccountByIban(String iban);

    Account getAccountByAccountNumber(String accountNumber);
    Account getAccountByIban(String iban);


}

package com.example.banksystem.repository;

import com.example.banksystem.model.Account;
import com.example.banksystem.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByIbanAndBankAndAccountBalanceAndAccountNumber
            (String iban, Bank bank, Double accountBalance, String accountNumber);


    default boolean AccountExists(Account account) {
        return existsByIbanAndBankAndAccountBalanceAndAccountNumber(
                account.getIban(), account.getBank(), account.getAccountBalance(),
                account.getAccountNumber());
    }

    boolean existsByClient_ClientId(Long id);
}

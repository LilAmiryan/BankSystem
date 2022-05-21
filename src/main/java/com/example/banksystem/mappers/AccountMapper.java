package com.example.banksystem.mappers;

import com.example.banksystem.dto.AccountDto;
import com.example.banksystem.dto.BankDto;
import com.example.banksystem.model.Account;
import com.example.banksystem.model.Bank;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {
    public Account toAccount(AccountDto accountDto){
        Account account=new Account();
        account.setIban(accountDto.getIban());
        account.setBank(accountDto.getBank());
        account.setAccountBalance(accountDto.getAccountBalance());
        account.setAccountNumber(accountDto.getAccountNumber());
        return account;
    }

    public AccountDto toAccountDto(Account account){
        AccountDto accountDto=new AccountDto();
        accountDto.setAccountBalance(account.getAccountBalance());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setBank(account.getBank());
        accountDto.setIban(account.getIban());
        return accountDto;
    }

    public List<AccountDto> mapAllToAccountDto(List<Account> accounts) {
        return accounts.stream()
                .map(this::toAccountDto)
                .collect(Collectors.toList());
    }
}

package com.example.banksystem.mappers;

import com.example.banksystem.dto.AccountDto;
import com.example.banksystem.dto.BankDto;
import com.example.banksystem.model.Account;
import com.example.banksystem.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    private BankMapper bankMapper;
    private ClientMapper clientMapper;

    @Autowired
    public AccountMapper(BankMapper bankMapper, ClientMapper clientMapper) {
        this.bankMapper = bankMapper;
        this.clientMapper = clientMapper;
    }

    public Account toAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setIban(accountDto.getIban());
        account.setBank(bankMapper.toBank(accountDto.getBankDto()));
        account.setAccountBalance(accountDto.getAccountBalance());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setClient(clientMapper.toClient(accountDto.getClientDto()));
        return account;
    }

    public AccountDto toAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountBalance(account.getAccountBalance());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setBankDto(bankMapper.toBankDto(account.getBank()));
        accountDto.setIban(account.getIban());
        accountDto.setClientDto(clientMapper.toClientDto(account.getClient()));
        return accountDto;
    }

    public List<AccountDto> mapAllToAccountDto(List<Account> accounts) {
        return accounts.stream()
                .map(this::toAccountDto)
                .collect(Collectors.toList());
    }
}

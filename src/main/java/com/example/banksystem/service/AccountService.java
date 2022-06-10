package com.example.banksystem.service;

import com.example.banksystem.dto.AccountDto;
import com.example.banksystem.mappers.AccountMapper;
import com.example.banksystem.mappers.ClientMapper;
import com.example.banksystem.model.Account;
import com.example.banksystem.model.enums.ErrorType;
import com.example.banksystem.repository.AccountRepository;
import com.example.banksystem.repository.BankRepository;
import com.example.banksystem.repository.ClientRepository;
import com.example.banksystem.response.account.AccountBalanceDecreaseResponse;
import com.example.banksystem.response.account.AccountBalanceIncreaseResponse;
import com.example.banksystem.response.account.AccountCreateResponse;
import com.example.banksystem.response.account.AccountDeleteResponse;
import com.example.banksystem.validator.AccountValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;
    ClientRepository clientRepository;
    AccountValidator accountValidator;
    BankRepository bankRepository;
    ClientMapper clientMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          AccountMapper accountMapper,
                          ClientRepository clientRepository,
                          AccountValidator accountValidator,
                          BankRepository bankRepository,
                          ClientMapper clientMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.clientRepository = clientRepository;
        this.accountValidator = accountValidator;
        this.bankRepository = bankRepository;
        this.clientMapper = clientMapper;

    }

    public AccountCreateResponse createAccount(Long clientId) {
        ErrorType errorType = null;
        if (!accountValidator.isValidClientId(clientId)) {
            errorType = ErrorType.NOT_VALID;
        }
        if (!clientRepository.existsClientByClientId(clientId)) {
            errorType = ErrorType.NOT_FOUND;
        }
        if (errorType != null) {
            return new AccountCreateResponse(errorType);
        }
        Account accountToSave = new Account();

        accountToSave.setAccountNumber(generateAccountNumber());
        accountToSave.setIban(generateIBAN());
        accountToSave.setClient(clientRepository.findById(clientId).get());
        accountToSave.setBank(bankRepository.findById(1L).get());
        accountToSave.setAccountBalance(0.0);

        Account savedAccount = accountRepository.save(accountToSave);
        return new AccountCreateResponse(accountMapper.toAccountDto(savedAccount));
    }

    public AccountDeleteResponse deleteAccount(String accountNumber) {
        ErrorType errorType = null;
        if (!accountValidator.isValidAccountNumber(accountNumber)) {
            errorType = ErrorType.NOT_VALID;
        } else {
            if (!accountRepository.existsAccountByAccountNumber(accountNumber)) {
                errorType = ErrorType.NOT_FOUND;
            } else if (accountRepository.getAccountByAccountNumber(accountNumber).getAccountBalance() > 0) {
                errorType = ErrorType.POSITIVE_BALANCE;
            }
        }
        if (errorType != null) {
            return new AccountDeleteResponse(errorType);
        }
        //Ready for delete
        AccountDto deletedAccountDto = accountMapper.toAccountDto(
                accountRepository.getAccountByAccountNumber(accountNumber));
        accountRepository.delete(accountRepository.getAccountByAccountNumber(accountNumber));
        return new AccountDeleteResponse(deletedAccountDto);
    }

    public AccountBalanceIncreaseResponse increaseBalance(Double sum, String accountNumber) {
        ErrorType errorType = null;
        if (!accountValidator.isValidAccountNumber(accountNumber)) {
            errorType = ErrorType.NOT_VALID;
            return new AccountBalanceIncreaseResponse(errorType);
        }
        if (!accountRepository.existsAccountByAccountNumber(accountNumber)) {
            errorType = ErrorType.NOT_FOUND;
            return new AccountBalanceIncreaseResponse(errorType);
        }

        Account account = accountRepository.getAccountByAccountNumber(accountNumber);
        Double accountBalance = account.getAccountBalance();
        account.setAccountBalance(accountBalance + sum);
        accountRepository.save(account);
        AccountDto accountDto = accountMapper.toAccountDto(account);
        return new AccountBalanceIncreaseResponse(accountDto);

    }

    public AccountBalanceDecreaseResponse decreaseBalance(Double sum, String accountNumber) {
        ErrorType errorType = null;
        if (!accountValidator.isValidAccountNumber(accountNumber)) {
            errorType = ErrorType.NOT_VALID;
            return new AccountBalanceDecreaseResponse(errorType);
        }
        if (!accountRepository.existsAccountByAccountNumber(accountNumber)) {
            errorType = ErrorType.NOT_FOUND;
            return new AccountBalanceDecreaseResponse(errorType);
        }
        if (accountRepository.getAccountByAccountNumber(accountNumber).getAccountBalance() < sum) {
            errorType = ErrorType.INSUFFICIENT_BALANCE;
            return new AccountBalanceDecreaseResponse(errorType);
        }
        Account account = accountRepository.getAccountByAccountNumber(accountNumber);
        Double accountBalance = account.getAccountBalance();
        account.setAccountBalance(accountBalance - sum);
        accountRepository.save(account);
        AccountDto accountDto = accountMapper.toAccountDto(account);
        return new AccountBalanceDecreaseResponse(accountDto);
    }

    public String generateAccountNumber() {
        String accountNumber = null;
        do {
            accountNumber = "22011" + RandomStringUtils.randomNumeric(11);
        }
        while (accountRepository.existsAccountByAccountNumber(accountNumber));
        return accountNumber;
    }

    public String generateIBAN() {
        String iban = "AM";
        do {
            iban = iban + RandomStringUtils.randomNumeric(2) +
                    RandomStringUtils.randomAlphanumeric(8);
        } while (accountRepository.existsAccountByIban(iban));
        return iban;
    }

    public ResponseEntity<?>  transferFromAccountToAccount(Double amount, String fromAccountNumber, String toAccountNumber) {
        if (!accountValidator.isValidAccountNumber(fromAccountNumber) ||
                !accountValidator.isValidAccountNumber(toAccountNumber)) {


            return new ResponseEntity<>("Input valid account number", HttpStatus.BAD_REQUEST) ;
        }
        if (!accountRepository.existsAccountByAccountNumber(fromAccountNumber) ||
                !accountRepository.existsAccountByAccountNumber(toAccountNumber)) {
            return new ResponseEntity<>("No account with such account or card number.",HttpStatus.BAD_REQUEST);
        }
        Account fromAccount = accountRepository.getAccountByAccountNumber(fromAccountNumber);
        if (fromAccount.getAccountBalance() < amount) {
            return new ResponseEntity<>("Insufficient balance.",HttpStatus.BAD_REQUEST);
        }

        fromAccount.setAccountBalance(fromAccount.getAccountBalance() - amount);
        accountRepository.save(fromAccount);
        Account toAccount = accountRepository.getAccountByAccountNumber(toAccountNumber);
        toAccount.setAccountBalance(toAccount.getAccountBalance() + amount);
        accountRepository.save(toAccount);
        return new ResponseEntity<>("Success!",HttpStatus.OK);

    }
}

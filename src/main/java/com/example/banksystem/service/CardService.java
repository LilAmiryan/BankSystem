package com.example.banksystem.service;

import com.example.banksystem.model.Account;
import com.example.banksystem.model.Card;
import com.example.banksystem.model.enums.BalanceType;
import com.example.banksystem.model.enums.CardStatusType;
import com.example.banksystem.model.enums.CardType;
import com.example.banksystem.model.enums.ErrorType;
import com.example.banksystem.repository.AccountRepository;
import com.example.banksystem.repository.CardRepository;
import com.example.banksystem.repository.ClientRepository;
import com.example.banksystem.mappers.AccountMapper;
import com.example.banksystem.mappers.CardMapper;
import com.example.banksystem.response.card.CardCreateResponse;
import com.example.banksystem.response.card.TransferFromCardToAccountResponse;
import com.example.banksystem.validator.AccountValidator;
import com.example.banksystem.validator.CardValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CardService {
    private CardRepository cardRepository;
    private CardMapper cardMapper;
    private ClientRepository clientRepository;
    private CardValidator cardValidator;
    private AccountRepository accountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;
    private AccountValidator accountValidator;
    private static final AES aes = new AES();

    @Autowired
    public CardService(CardRepository cardRepository, CardMapper cardMapper, ClientRepository clientRepository,
                       CardValidator cardValidator, AccountRepository accountRepository,
                       AccountService accountService, AccountMapper accountMapper,
                       AccountValidator accountValidator) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
        this.clientRepository = clientRepository;
        this.cardValidator = cardValidator;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.accountValidator = accountValidator;
    }

    public CardCreateResponse createCard(Long clientId, CardType cardType, String iban) {
        final String pin = generatePin();
        String encryptedPin = encryptPin(pin);

        ErrorType errorType = null;
        if (!clientRepository.existsClientByClientId(clientId)) {
            errorType = ErrorType.NOT_FOUND;
            return new CardCreateResponse(errorType);
        }
//        if (!accountRepository.existsAccountByIban(iban)) {
//            errorType = ErrorType.NOT_VALID_ACCOUNT;
//            return new CardCreateResponse(errorType);
//        }
        if (!cardValidator.isValidCard(cardType)) {
            errorType = ErrorType.NOT_VALID;
            return new CardCreateResponse(errorType);
        }

        Card cardToSave = new Card();

        cardToSave.setCardType(cardType);
        cardToSave.setBalanceType(BalanceType.DEBIT);
        cardToSave.setCardNumber(generateCardNumber());
        cardToSave.setCreditBalance(0.0);
        cardToSave.setStatus(CardStatusType.ACTIVE);
        cardToSave.setCodeCVC(generateCodeCVC());
        cardToSave.setClient(clientRepository.getClientByClientId(clientId));
        cardToSave.setAccount(accountRepository.getAccountByIban(iban));
        cardToSave.setExpireDate(LocalDate.now().plusYears(2));

        cardToSave.setPin(encryptedPin);

        Card savedCard = cardRepository.save(cardToSave);
        return new CardCreateResponse(cardMapper.toCardDto(savedCard));
    }


    public TransferFromCardToAccountResponse
    transferFromCardToAccount(Double amount, String fromCardNumber, String toAccountNumber) {
        ErrorType errorType = null;
        if (!cardValidator.isValidCardNumber(fromCardNumber) ||
        !accountValidator.isValidAccountNumber(toAccountNumber)){
            errorType = ErrorType.NOT_VALID;
            return new TransferFromCardToAccountResponse(errorType);
        }
        if (!cardRepository.existsCardByCardNumber(fromCardNumber) ||
                !accountRepository.existsAccountByAccountNumber(toAccountNumber)){
             errorType = ErrorType.NOT_FOUND;
             return new TransferFromCardToAccountResponse(errorType);
        }
        Account fromAccount = cardRepository.getCardByCardNumber(fromCardNumber).getAccount();
        if (fromAccount.getAccountBalance() < amount) {
            errorType = ErrorType.INSUFFICIENT_BALANCE;
            return new TransferFromCardToAccountResponse(errorType);
        }

        fromAccount.setAccountBalance(fromAccount.getAccountBalance() - amount);
        accountRepository.save(fromAccount);
        Account toAccount = accountRepository.getAccountByAccountNumber(toAccountNumber);
        toAccount.setAccountBalance(toAccount.getAccountBalance()+amount);
        accountRepository.save(toAccount);
                return new TransferFromCardToAccountResponse();

    }

//    public TransferFromCardToCardResponse transferToCard(amount, fromCardNumber, toCardNumber){
//        ErrorType errorType = null;
//        if (!cardValidator.isValidCardNumber(fromCardNumber) ||
//                !cardValidator.isValidCardNumber(toCardNumber)){
//            errorType = ErrorType.NOT_VALID;
//            return new TransferFromCardToAccountResponse(errorType);
//        }
//        if (!cardRepository.existsCardByCardNumber(fromCardNumber) ||
//                !accountRepository.existsAccountByAccountNumber(toAccountNumber)){
//            errorType = ErrorType.NOT_FOUND;
//            return new TransferFromCardToAccountResponse(errorType);
//        }
//        Account fromAccount = cardRepository.getCardByCardNumber(fromCardNumber).getAccount();
//        if (fromAccount.getAccountBalance() < amount) {
//            errorType = ErrorType.INSUFFICIENT_BALANCE;
//            return new TransferFromCardToAccountResponse(errorType);
//        }
//
//        fromAccount.setAccountBalance(fromAccount.getAccountBalance() - amount);
//        accountRepository.save(fromAccount);
//        Account toAccount = accountRepository.getAccountByAccountNumber(toAccountNumber);
//        toAccount.setAccountBalance(toAccount.getAccountBalance()+amount);
//        accountRepository.save(toAccount);
//        return new TransferFromCardToAccountResponse();
//
//    }


    public String generateCardNumber() {
        String cardNumber = null;
        do {
            cardNumber = RandomStringUtils.randomNumeric(16);
        }
        while (cardRepository.existsCardByCardNumber(cardNumber));
        return cardNumber;
    }

    public String generateCodeCVC() {
        return RandomStringUtils.randomNumeric(3);
    }

    public String generatePin() {
        return RandomStringUtils.randomNumeric(4);
    }

    public String encryptPin(String pin) {
        String encryptedMessage = null;
        try {
            aes.init();
            encryptedMessage = aes.encrypt(pin);
        } catch (Exception ignored) {
        }
        return encryptedMessage;
    }

//    public String decryptGeneratedPin(String encryptedPin) {
//        try {
//            aes.init();
//            String encryptedMessage = aes.encrypt(pin);
//            String decryptedMessage = aes.decrypt(encryptedMessage);
//
////            System.err.println("Encrypted Message : " + encryptedMessage);
////            System.err.println("Decrypted Message : " + decryptedMessage);
//        } catch (Exception ignored) {
//        }
//
//        return
//    }

}



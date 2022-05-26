package com.example.banksystem.service;

import com.example.banksystem.dto.CardDto;
import com.example.banksystem.mappers.CardMapper;
import com.example.banksystem.model.Card;
import com.example.banksystem.repository.CardRepository;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    CardRepository cardRepository;
    CardMapper cardMapper;
    ClientRepository clientRepository;
//    CardValidator cardValidator;
//
//
//    @Autowired
//    public CardService(CardRepository cardRepository, CardMapper cardMapper, ClientRepository clientRepository,
//                       CardValidator cardValidator) {
//        this.cardRepository = cardRepository;
//        this.cardMapper = cardMapper;
//        this.clientRepository = clientRepository;
//        this.cardValidator = cardValidator;
//    }
//
//    public CardCreateResponse createCard(Long clientId, CardDto cardDto) {
//        ErrorType errorType = null;
////        if (!cardValidator.isValidClientId(clientId)) {
////            errorType = ErrorType.NOT_VALID;
////        }
//        if (!clientRepository.existsClientByClientId(clientId)) {
//            errorType = ErrorType.NOT_FOUND;
//            return new CardCreateResponse(errorType);
//        }
//        if (!cardValidator.isValidCard(cardDto)) {
//            errorType = ErrorType.NOT_VALID;
//            return new CardCreateResponse(errorType);
//        }
//        cardDto
//        cardToSave.
//                cardToSave.setIban(generateIBAN());
//        cardToSave.setClient(clientRepository.findById(clientId).get());
//        cardToSave.setBank(bankRepository.findById(1L).get());
//        cardToSave.setAccountBalance(0.0);
//
//        Card cardToSave = cardMapper.toCard(cardDto);
//        Account savedAccount = accountRepository.save(accountToSave);
//
//        return new CardCreateResponse(accountMapper.toAccountDto(savedAccount));
//    }
//
//    public String generateAccountNumber() {
//        String accountNumber = null;
//        do {
//            accountNumber = "22011" + RandomStringUtils.randomNumeric(11);
//        }
//        while (accountRepository.existsAccountByAccountNumber(accountNumber));
//        return accountNumber;
//    }

}

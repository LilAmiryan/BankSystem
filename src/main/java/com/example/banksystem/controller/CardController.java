package com.example.banksystem.controller;

import com.example.banksystem.model.enums.CardType;
import com.example.banksystem.model.enums.ErrorType;
import com.example.banksystem.response.card.CardCreateResponse;
import com.example.banksystem.response.card.TransferFromCardToAccountResponse;
import com.example.banksystem.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
public class CardController {
    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createCard(@PathVariable("id") Long clientId, @RequestParam CardType cardType,
                                        @RequestParam String iban) {
        CardCreateResponse cardCreateResponse = cardService.createCard(clientId, cardType, iban);
        ErrorType errorType = cardCreateResponse.getErrorType();

        if (errorType != null) {
            return cardCreateResponse.onFailure(errorType);
        }
        return cardCreateResponse.onSuccess();
    }

    @PutMapping("/transferCardAccount")
    public ResponseEntity<?> transferFromCardToAccount(@RequestParam Double amount,
                                                       @RequestParam String fromCardNumber,
                                                       @RequestParam String toAccountNumber) {
        TransferFromCardToAccountResponse transferResponse = cardService.
                transferFromCardToAccount(amount, fromCardNumber, toAccountNumber);
        ErrorType errorType = transferResponse.getErrorType();
        if (errorType != null) {
            return transferResponse.onFailure(errorType);
        }
        return transferResponse.onSuccess();

    }

    @PutMapping("/transferCardCard")
    public ResponseEntity<?> transferFromCardToCard(@RequestParam Double amount,
                                                       @RequestParam String fromCardNumber,
                                                       @RequestParam String toAccountNumber) {
        TransferFromCardToAccountResponse transferResponse = cardService.
                transferFromCardToAccount(amount, fromCardNumber, toAccountNumber);
        ErrorType errorType = transferResponse.getErrorType();
        if (errorType != null) {
            return transferResponse.onFailure(errorType);
        }
        return transferResponse.onSuccess();

    }
}

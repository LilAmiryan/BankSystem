package com.example.banksystem.controller;

import com.example.banksystem.dto.CardDto;
import com.example.banksystem.model.enumtypeofmodelfields.ErrorType;
import com.example.banksystem.repository.ClientRepository;
import com.example.banksystem.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/card")
public class CardController {
    private CardService cardService;

    @Autowired
    public CardController(CardService cardService, ClientRepository clientRepository) {
        this.cardService = cardService;
    }
//
//    @PostMapping("/{id}")
//    public ResponseEntity<?> createCard(@PathVariable("id") Long clientId, CardDto cardDto) {
//        CardCreateResponse cardCreateResponse = cardService.createCard(clientId, cardDto);
//        ErrorType errorType = cardCreateResponse.getErrorType();
//
//        if (errorType != null) {
//            return cardCreateResponse.onFailure(errorType);
//        }
//        return cardCreateResponse.onSuccess();
//    }
}

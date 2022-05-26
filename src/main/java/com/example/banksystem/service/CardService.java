package com.example.banksystem.service;

import com.example.banksystem.dto.CardDto;
import com.example.banksystem.repository.mappers.CardMapper;
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

    @Autowired
    public CardService(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    public Optional<CardDto> createCard(CardDto cardDto) {
        Card card = cardMapper.toCard(cardDto);
        String cardNumber;
        do {
            cardNumber = String.valueOf(
                    new RandomDataGenerator().
                            nextLong(1000000000000000L, 9999999999999999L));


        } while (!cardRepository.existsByCardNumber(cardNumber));
        card.setCardNumber(cardNumber);
        return Optional.of(cardMapper.toCardDto(card));

    }


}

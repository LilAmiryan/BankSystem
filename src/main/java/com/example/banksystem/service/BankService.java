package com.example.banksystem.service;

import com.example.banksystem.dto.BankDto;
import com.example.banksystem.repository.mappers.BankMapper;
import com.example.banksystem.model.Bank;
import com.example.banksystem.model.enumtypeofmodelfields.ErrorType;
import com.example.banksystem.repository.BankRepository;
import com.example.banksystem.response.bank.BankUpdateResponse;
import com.example.banksystem.validator.BankValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BankService {
    BankRepository bankRepository;
    BankMapper bankMapper;
    BankValidator bankValidator;

    @Autowired
    public BankService(BankRepository bankRepository, BankMapper bankMapper, BankValidator bankValidator) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
        this.bankValidator = bankValidator;
    }

    // DO NOT USE THIS
    public Optional<BankDto> deleteBank(Long id) {
        try {
            Bank bank = bankRepository.findById(id).get();
            bankRepository.deleteById(id);
            return Optional.of(bankMapper.toBankDto(bank));
        } catch (EmptyResultDataAccessException | NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    public BankUpdateResponse updateBank(BankDto bankDto, Long bankId) {
        ErrorType errorType = null;
        if (!bankValidator.isValidBank(bankDto)) {
            errorType = ErrorType.NOT_VALID;
        }
        if (errorType != null) {
            return new BankUpdateResponse(errorType);
        }

        Bank bank = bankMapper.toBank(bankDto);
        bank.setBankId(bankId);
        Bank savedBank = bankRepository.save(bank);
        return new BankUpdateResponse(bankMapper.toBankDto(savedBank));
    }
}

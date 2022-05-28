package com.example.banksystem.controller;

import com.example.banksystem.dto.AddressDto;
import com.example.banksystem.model.enums.ErrorType;
import com.example.banksystem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.banksystem.response.address.AddressCreateResponse;
import com.example.banksystem.response.address.AddressDeleteResponse;
import com.example.banksystem.response.address.AddressUpdateResponse;

import java.util.Optional;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody AddressDto addressDto) {

        if (addressDto == null) {
            return new AddressCreateResponse().onFailure();
        }

        Optional<AddressDto> optionalAddressDto = addressService.createAddress(addressDto);
        if (optionalAddressDto.isEmpty()) {
            return new AddressCreateResponse().onFailure();
        }

        return new AddressCreateResponse(optionalAddressDto.get()).onSuccess();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") Long id){
        AddressDeleteResponse deleteResponse = addressService.deleteAddress(id);

        ErrorType deleteErrorType = deleteResponse.getErrorType();
        if (deleteErrorType != null) {
            return deleteResponse.onFailure(deleteErrorType);
        }
        return deleteResponse.onSuccess();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@RequestBody AddressDto addressDto,
                                           @PathVariable("id") Long id)  {

        AddressUpdateResponse updatedResponse = addressService.updateAddress(addressDto, id);
        ErrorType errorType=updatedResponse.getErrorType();
        if(errorType!=null) {
            return updatedResponse.onFailure(errorType);
        }

        return updatedResponse.onSuccess();

    }
}
package com.example.banksystem.response.address;

import com.example.banksystem.dto.AddressDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AddressCreateResponse {

    private AddressDto addressDto;

    public AddressCreateResponse() {
    }

    public AddressCreateResponse(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public ResponseEntity<?> onFailure() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Address already exists.");
    }

    public ResponseEntity<AddressDto> onSuccess() {
        return ResponseEntity.ok().body(addressDto);
    }

}
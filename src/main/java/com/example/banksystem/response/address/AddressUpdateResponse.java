package com.example.banksystem.response.address;

import com.example.banksystem.dto.AddressDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AddressUpdateResponse {

    private AddressDto addressDto;

    public AddressUpdateResponse() {
    }

    public AddressUpdateResponse(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public ResponseEntity<?> onFailure() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No address with such id.");
    }

    public ResponseEntity<AddressDto> onSuccess() {
        return ResponseEntity.ok().body(addressDto);
    }


}

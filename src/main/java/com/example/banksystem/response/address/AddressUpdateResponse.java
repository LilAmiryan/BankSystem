package com.example.banksystem.response.address;

import com.example.banksystem.dto.AddressDto;
import com.example.banksystem.model.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AddressUpdateResponse {

    private AddressDto addressDto;
    private ErrorType errorType;


    public AddressUpdateResponse(ErrorType errorType) {
        this.errorType = errorType;
    }


    public AddressUpdateResponse(AddressDto addressDto) {
        this.addressDto = addressDto;
    }


    public ResponseEntity<?> onFailure(ErrorType errorType) {
        ResponseEntity<?> response;
        switch (errorType) {
            case NOT_VALID -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Input parameter(s) are wrong.");
            case NOT_FOUND -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("No address with such Id.");
            default -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Unknown error");
        }
        return response;
    }

    public ResponseEntity<AddressDto> onSuccess() {
        return ResponseEntity.ok().body(addressDto);
    }

    public ErrorType getErrorType() {
        return errorType;
    }

}

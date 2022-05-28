package com.example.banksystem.response.address;

import com.example.banksystem.dto.AddressDto;
import com.example.banksystem.model.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AddressDeleteResponse {
    private AddressDto addressDto;
    private ErrorType errorType;

    public AddressDeleteResponse(ErrorType errorType) {
        this.errorType = errorType;
    }

    public AddressDeleteResponse(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public ResponseEntity<?> onFailure(ErrorType errorType) {
        ResponseEntity<?> response;
        switch (errorType) {
            case NOT_FOUND -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("No address with such Id.");
            case ALREADY_EXISTS -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("This address is in use by another client.");
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

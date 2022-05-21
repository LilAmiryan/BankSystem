package com.example.banksystem.dto;

import com.example.banksystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private String firstName;
    private String lastName;
    private String ssn;	//Unique
    private java.time.LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
    private Address address;	//fk_address_client_ManyToOne
    private LocalDate registerDate;

}

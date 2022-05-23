package com.example.banksystem.dto;

import com.example.banksystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private String firstName;
    private String lastName;
    private String ssn;	//Unique
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
    private Address address;
    private LocalDate registerDate;

}

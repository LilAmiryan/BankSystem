package com.example.banksystem.repository;

import com.example.banksystem.model.Address;
import com.example.banksystem.model.Client;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByFirstNameAndSsnAndPhoneNumberAndDateOfBirthAndEmailAndRegisterDate
            (String firstName, String ssn, String phoneNumber,
             LocalDate dateOfBirth, String email,
             LocalDate registerDate);

    default boolean clientExist(Client client) {
        return
                existsByFirstNameAndSsnAndPhoneNumberAndDateOfBirthAndEmailAndRegisterDate(
                        client.getFirstName(), client.getSsn(), client.getPhoneNumber(),
                        client.getDateOfBirth(), client.getEmail(),
                        client.getRegisterDate()
                );
    }
    boolean existsByAddress_AddressId(Long id);
}

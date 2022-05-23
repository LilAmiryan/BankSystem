package com.example.banksystem.repository;

import com.example.banksystem.model.Address;
import com.example.banksystem.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ClientRepository extends JpaRepository<Client,Long> {
     boolean existsByFirstNameAndSsnAndPhoneNumberAndAddressAndDateOfBirthAndEmailAndRegisterDate
             (String firstName, String ssn, String phoneNumber,
              Address address, LocalDate dateOfBirth, String email,
              LocalDate registerDate);

    default boolean clientExist(Client client) {
        return
                existsByFirstNameAndSsnAndPhoneNumberAndAddressAndDateOfBirthAndEmailAndRegisterDate(
                        client.getFirstName(),client.getSsn(),client.getPhoneNumber(),
                        client.getAddress(),client.getDateOfBirth(),client.getEmail(),
                        client.getRegisterDate()
                );
    }
    boolean existsByAddress_AddressId(Long id);
}

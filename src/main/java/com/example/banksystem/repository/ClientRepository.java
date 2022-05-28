package com.example.banksystem.repository;

import com.example.banksystem.model.Address;
import com.example.banksystem.model.Client;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
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
    boolean existsClientByClientId(Long id);

    Client getClientByClientId(Long id);

}

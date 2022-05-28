package com.example.banksystem.repository;

import com.example.banksystem.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
//    boolean existsByFirstNameAndSsnAndPhoneNumberAndAddressAndDateOfBirthAndEmailAndRegisterDate
//            (String firstName, String ssn, String phoneNumber,
//             Address address, LocalDate dateOfBirth, String email,
//             LocalDate registerDate);

     boolean
     existsByFirstNameAndLastNameAndSsnAndDateOfBirthAndPhoneNumberAndEmailAndAddress_AddressIdAndRegisterDate
             (String firstName, String lastName, String ssn,
              LocalDate dateOfBirth, String phoneNumber,
              String email,
              Long address_addressId, LocalDate registerDate);

    default boolean clientExist(Client client) {
        return
                existsByFirstNameAndLastNameAndSsnAndDateOfBirthAndPhoneNumberAndEmailAndAddress_AddressIdAndRegisterDate
                        (client.getFirstName(),
                                client.getLastName(),
                                client.getSsn(),
                                client.getDateOfBirth(),
                                client.getPhoneNumber(),
                                client.getEmail(),
                                client.getClientId(),
                                client.getRegisterDate()
                );
    }


//    default boolean clientExist(Client client) {
//        return
//                existsByFirstNameAndSsnAndPhoneNumberAndAddressAndDateOfBirthAndEmailAndRegisterDate(
//                        client.getFirstName(), client.getSsn(), client.getPhoneNumber(),
//                        client.getAddress(), client.getDateOfBirth(), client.getEmail(),
//                        client.getRegisterDate()
//                );
//    }

    boolean existsByAddress_AddressId(Long id);

    boolean existsClientByClientId(Long id);

    Client getClientByClientId(Long id);

}

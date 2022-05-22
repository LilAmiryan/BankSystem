package com.example.banksystem.mappers;

import com.example.banksystem.dto.ClientDto;
import com.example.banksystem.model.Client;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ClientMapper {

    public Client toClient(ClientDto clientDto) {
        Client client = new Client();
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setSsn(clientDto.getSsn());
        client.setDateOfBirth(clientDto.getDateOfBirth());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setEmail(clientDto.getEmail());
        client.setAddress(clientDto.getAddress());
        client.setRegisterDate(clientDto.getRegisterDate());
        return client;
    }

    public ClientDto toClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setAddress(client.getAddress());
        clientDto.setEmail(client.getEmail());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        clientDto.setRegisterDate(client.getRegisterDate());
        clientDto.setSsn(client.getSsn());
        clientDto.setDateOfBirth(client.getDateOfBirth());
        return clientDto;
    }

    public List<ClientDto> toClientDto(List<Client> clients) {
        return clients.stream().map(this::toClientDto).collect(Collectors.toList());
    }
}

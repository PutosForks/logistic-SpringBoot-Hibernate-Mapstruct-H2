package com.logistic.task.service;

import com.logistic.task.dto.ClientDto;
import com.logistic.task.entity.Address;
import com.logistic.task.entity.Client;

import java.util.List;
import java.util.Optional;


/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 12.05.2019
 */

public interface ClientService{

    List<Client> findAll();

    Optional<Client> findById(Long id);

    ClientDto save(ClientDto clientDto) ;

    Client saveEntity(Client client);

    boolean deleteById(Long id);

    ClientDto update(long id, ClientDto clientDto);

    List<Address> getAddressByClientId(long clientId);
}

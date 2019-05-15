package com.logistic.task.service;

import com.logistic.task.dto.ClientDto;
import com.logistic.task.entity.Address;
import com.logistic.task.mapper.ClientMapper;
import com.logistic.task.repository.ClientRepository;
import com.logistic.task.entity.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Slf4j
@RequiredArgsConstructor

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;


    @Override
    @Transactional
    public List<Client> findAll() {
        log.info("Getting all clients");
        return clientRepository.findAll();
    }
    @Override
    @Transactional
    public Optional<Client> findById(Long id) {
        log.info("Getting client by id {}", id);
        return clientRepository.findById(id);
    }

    @Override
    @Transactional
    public ClientDto save(ClientDto clientDto) {
        log.info("Saving client {}", clientDto);
        Client client = clientMapper.toEntity(clientDto);
        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    @Override
    @Transactional
    public Client saveEntity(Client client) {
        log.info("Save ClientEntity {}", client);
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (!client.isPresent()) {
            log.debug("Can't delete client. Client doesn't exist " + id);
            return false;
        }
        log.info("Deleting client {}", client.get());
        clientRepository.delete(client.get());
        return true;
    }

    @Override
    @Transactional
    public ClientDto update(long id, ClientDto clientDto) {
        Client source = clientMapper.toEntity(clientDto);
        log.info("Updating client {}", source);
        Client target = clientRepository.findById(id).get();

        // переписать создание
        // target  разобраться почему не работает
        // копирование copyProperties(target, source)


      /*  try {
            copyProperties(target, source);
        } catch (Exception e) {
            log.error("Can't get properties from object to updatable object for client", e);
        }*/
        target.setPhoneNumber(source.getPhoneNumber());
        target.setAddress(source.getAddress());
        target.setName(source.getName());
        target.setId(id);

        clientRepository.save(target);
        return clientMapper.toDto(target);
    }

    @Override
    public List<Address> getAddressByClientId(long clientId) {
        log.info("Getting addresses by client id {}",clientId);
        Optional<Client> byId = clientRepository.findById(clientId);
        Client client = byId.get();

        System.out.println("-----------------------------------------------------------------------");
        System.out.println(client.toString());
        System.out.println("-----------------------------------------------------------------------");


        return byId.get().getAddress();

    }
}
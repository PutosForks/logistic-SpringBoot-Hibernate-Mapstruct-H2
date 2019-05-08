package com.logistic.task.service;

import com.logistic.task.entity.Address;
import com.logistic.task.repository.ClientRepository;
import com.logistic.task.entity.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Slf4j
@RequiredArgsConstructor

@Service
public class ClientService {
    private final ClientRepository productRepository;

    public List<Client> findAll() {
        return productRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return productRepository.findById(id);
    }


    public Client save(Client stock) {
        return productRepository.save(stock);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Address returnAddressByClientId(Long id) {
      return findById(id).get().getAddress();
    }
}
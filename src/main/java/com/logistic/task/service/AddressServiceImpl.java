package com.logistic.task.service;

import com.logistic.task.entity.Address;
import com.logistic.task.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@RequiredArgsConstructor

@Service
public class AddressServiceImpl {
    private final AddressRepository addressRepository;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    public Address save(Address stock) {
        return addressRepository.save(stock);
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}

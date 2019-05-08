package com.logistic.task.controller;

import com.logistic.task.dto.AddressDto;

import com.logistic.task.entity.Address;

import com.logistic.task.mapper.AddressMapper;

import com.logistic.task.service.AddressServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressServiceImpl addressService;
    private final AddressMapper addressMapper;

    @GetMapping
    public ResponseEntity<List<AddressDto>> findAll() {
        return ResponseEntity.ok(addressMapper.toDto(addressService.findAll()));
    }

    @PostMapping
    public ResponseEntity<AddressDto> create(@RequestBody AddressDto addressDto) {
        addressService.save(addressMapper.toEntity(addressDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(addressDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable Long id) {
        Optional<Address> product = addressService.findById(id);

        return ResponseEntity.ok(addressMapper.toDto(product.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> update(@PathVariable Long id, @RequestBody AddressDto addressDto) {
        Address address = addressMapper.toEntity(addressDto);
        address.setId(id);

        addressService.save(address);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(addressDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        addressService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}




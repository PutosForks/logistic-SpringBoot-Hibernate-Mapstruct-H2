package com.logistic.task.controller;

import com.logistic.task.dto.AddressDto;

import com.logistic.task.dto.ClientDto;
import com.logistic.task.entity.Address;

import com.logistic.task.entity.Client;
import com.logistic.task.mapper.AddressMapper;

import com.logistic.task.service.AddressService;
import com.logistic.task.service.AddressServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static javafx.beans.binding.Bindings.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping
    public ResponseEntity<List<AddressDto>> findAll() {
        return ResponseEntity.ok(addressMapper.toDto(addressService.findAll()));
    }

    @PostMapping
            (produces = MediaType.
                    APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid AddressDto addressDto) {
        addressDto = addressService.save(addressDto);
        return ResponseEntity.
                status(HttpStatus.CREATED).body(addressDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Address> client = addressService.findById(id);
        if (!client.isPresent()) {
            return new ResponseEntity<>
                    (String.format("No Client found for ID %d", id), NOT_FOUND);
        }
        return ResponseEntity.
                ok(addressMapper.toDto(client.get()));
    }


    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> update(final @PathVariable Long id,
                                             @RequestBody AddressDto addressDto) {
        addressDto = addressService.update(id, addressDto);
        return ResponseEntity.
                status(HttpStatus.ACCEPTED).body(addressDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if(!addressService.findById(id).isPresent()){
            return new ResponseEntity<>
                    (String.format("No Client found for ID %d", id), NOT_FOUND);
        }
        addressService.deleteById(id);

        return ResponseEntity.
                status(HttpStatus.ACCEPTED).build();
    }
}




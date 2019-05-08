package com.logistic.task.controller;

import com.logistic.task.dto.AddressDto;
import com.logistic.task.dto.ClientDto;
import com.logistic.task.entity.Address;
import com.logistic.task.entity.Client;
import com.logistic.task.mapper.AddressMapper;
import com.logistic.task.mapper.ClientMapper;
import com.logistic.task.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final AddressMapper addressMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientDto>> findAll() {
        return ResponseEntity.ok(clientMapper.toDto(clientService.findAll()));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> create(@RequestBody @Valid ClientDto clientDto) {
        clientService.save(clientMapper.toEntity(clientDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDto);
    }

    @GetMapping(value = "/{id}/address",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDto> returnAddressByClientId(@PathVariable Long id) {
        Address address = clientService.returnAddressByClientId(id);

        return ResponseEntity.ok(addressMapper.toDto(address));
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        Optional<Client> product = clientService.findById(id);
        return ResponseEntity.ok(clientMapper.toDto(product.get()));
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> update(@PathVariable @Valid Long id, @RequestBody @Valid ClientDto clientDto) {
      Client client = clientMapper.toEntity(clientDto);
        client.setId(id);

        clientService.save(client);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientDto);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable @Valid Long id) {
        if(!clientService.findById(id).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        clientService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
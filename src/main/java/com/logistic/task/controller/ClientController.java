package com.logistic.task.controller;

import com.logistic.task.dto.ClientDto;
import com.logistic.task.entity.Address;
import com.logistic.task.entity.Client;
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

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;


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

    @GetMapping
            (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientDto>> findAll() {
        return ResponseEntity.
                ok(clientMapper.toDto(clientService.findAll()));
    }

    @PostMapping
            (produces = MediaType.
                    APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid ClientDto clientDto) {
        clientDto = clientService.save(clientDto);
        return ResponseEntity.
                status(HttpStatus.CREATED).body(clientDto);
    }


    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        Optional<Client> client = clientService.findById(id);
        if (!client.isPresent()) {
            return new ResponseEntity<>
                    (format("No Client found for ID %d", id), NOT_FOUND);
        }
        return ResponseEntity.
                ok(clientMapper.toDto(client.get()));
    }

    @GetMapping("{clientId}/address")
    public ResponseEntity<?> getAddressByClientId(@PathVariable long clientId) {
        List<Address> addressesDto = clientService.getAddressByClientId(clientId);
        if (addressesDto == null) {
            return new ResponseEntity<>(format("Client %d or address doesn't exist", clientId), NOT_FOUND);
        }
        return new ResponseEntity<>(addressesDto, OK);
    }

    @PutMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<@Valid ClientDto> update(@PathVariable("id") Long id, @RequestBody @Valid ClientDto clientDto) {
        clientDto = clientService.update(id, clientDto);
                return ResponseEntity.
                        status(HttpStatus.ACCEPTED).body(clientDto);
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        if(!clientService.findById(id).isPresent()){
            return new ResponseEntity<>
                    (format("No Client found for ID %d", id), NOT_FOUND);
        }
        clientService.deleteById(id);

        return ResponseEntity.
                status(HttpStatus.ACCEPTED).build();
    }
}
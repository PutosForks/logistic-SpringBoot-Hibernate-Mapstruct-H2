package com.logistic.task.controller;

import com.logistic.task.dto.FlightDto;
import com.logistic.task.entity.Flight;
import com.logistic.task.mapper.FlightMapper;
import com.logistic.task.service.FlightService;
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

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 16.05.2019
 */
@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService service;
    private final FlightMapper mapper;

    @GetMapping
            (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FlightDto>> findAll() {
        return ResponseEntity.
                ok(mapper.toDto(service.findAll()));
    }

    @PostMapping
            (produces = MediaType.
                    APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid FlightDto flightDto) {
        flightDto = service.save(flightDto);
        return ResponseEntity.
                status(HttpStatus.CREATED).body(flightDto);
    }


    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        Optional<Flight> flight = service.findById(id);
        if (!flight.isPresent()) {
            return new ResponseEntity<>
                    (format("No Flight found for ID %d", id), NOT_FOUND);
        }
        return ResponseEntity.
                ok(mapper.toDto(flight.get()));
    }

    @PutMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<@Valid FlightDto> update(@PathVariable("id") Long id,
                                                   @RequestBody @Valid FlightDto flightDto) {
        flightDto = service.update(id, flightDto);
        return ResponseEntity.
                status(HttpStatus.ACCEPTED).body(flightDto);
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        if(!service.findById(id).isPresent()){
            return new ResponseEntity<>
                    (format("No Flight found for ID %d", id), NOT_FOUND);
        }
        service.deleteById(id);

        return ResponseEntity.
                status(HttpStatus.ACCEPTED).build();
    }

}

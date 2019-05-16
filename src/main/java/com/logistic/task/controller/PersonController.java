package com.logistic.task.controller;

import com.logistic.task.dto.PersonDto;
import com.logistic.task.entity.Person;
import com.logistic.task.mapper.PersonMapper;
import com.logistic.task.service.PersonService;
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
@RequestMapping("/person")
public class PersonController {


    private final PersonService service;
    private final PersonMapper mapper;

    @GetMapping
            (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDto>> findAll() {
        return ResponseEntity.
                ok(mapper.toDto(service.findAll()));
    }

    @PostMapping
            (produces = MediaType.
                    APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid PersonDto personDto) {
        personDto = service.save(personDto);
        return ResponseEntity.
                status(HttpStatus.CREATED).body(personDto);
    }


    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        Optional<Person> person = service.findById(id);
        if (!person.isPresent()) {
            return new ResponseEntity<>
                    (format("No Person found for ID %d", id), NOT_FOUND);
        }
        return ResponseEntity.
                ok(mapper.toDto(person.get()));
    }

    @PutMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<@Valid PersonDto> update(@PathVariable("id") Long id, @RequestBody @Valid PersonDto personDto) {
        personDto = service.update(id, personDto);
        return ResponseEntity.
                status(HttpStatus.ACCEPTED).body(personDto);
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        if(!service.findById(id).isPresent()){
            return new ResponseEntity<>
                    (format("No Person found for ID %d", id), NOT_FOUND);
        }
        service.deleteById(id);

        return ResponseEntity.
                status(HttpStatus.ACCEPTED).build();
    }

}

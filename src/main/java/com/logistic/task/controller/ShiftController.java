package com.logistic.task.controller;

import com.logistic.task.dto.PersonDto;
import com.logistic.task.dto.ShiftDto;
import com.logistic.task.entity.Person;
import com.logistic.task.entity.Shift;
import com.logistic.task.mapper.PersonMapper;
import com.logistic.task.mapper.ShiftMapper;
import com.logistic.task.service.PersonService;
import com.logistic.task.service.ShiftService;
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
@RequestMapping("/shift")
public class ShiftController {



    private final ShiftService service;
    private final ShiftMapper mapper;

    @GetMapping
            (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShiftDto>> findAll() {
        return ResponseEntity.
                ok(mapper.toDto(service.findAll()));
    }

    @PostMapping
            (produces = MediaType.
                    APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid ShiftDto shiftDto) {
        shiftDto = service.save(shiftDto);
        return ResponseEntity.
                status(HttpStatus.CREATED).body(shiftDto);
    }


    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        Optional<Shift> shift = service.findById(id);
        if (!shift.isPresent()) {
            return new ResponseEntity<>
                    (format("No Shift found for ID %d", id), NOT_FOUND);
        }
        return ResponseEntity.
                ok(mapper.toDto(shift.get()));
    }

    @PutMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<@Valid ShiftDto> update(@PathVariable("id") Long id,
                                                   @RequestBody @Valid ShiftDto shiftDto) {
        shiftDto = service.update(id, shiftDto);
        return ResponseEntity.
                status(HttpStatus.ACCEPTED).body(shiftDto);
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        if(!service.findById(id).isPresent()){
            return new ResponseEntity<>
                    (format("No Shift found for ID %d", id), NOT_FOUND);
        }
        service.deleteById(id);

        return ResponseEntity.
                status(HttpStatus.ACCEPTED).build();
    }


}

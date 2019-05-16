package com.logistic.task.controller;

import com.logistic.task.dto.CrewDto;
import com.logistic.task.entity.Crew;
import com.logistic.task.mapper.CrewMapper;
import com.logistic.task.service.CrewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 15.05.2019
 */

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/crew")
public class CrewController {

    private final CrewService crewService;
    private final CrewMapper crewMapper;

    @GetMapping
    public ResponseEntity<List<CrewDto>> findAll() {
        return ResponseEntity.ok(crewMapper.toDto(crewService.findAll()));
    }

    @PostMapping
            (produces = MediaType.
                    APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid CrewDto crewDto) {
        crewDto = crewService.save(crewDto);
        return ResponseEntity.
                status(HttpStatus.CREATED).body(crewDto);
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<Crew> crew = crewService.findById(id);
        if (!crew.isPresent()) {
            return new ResponseEntity<>
                    (String.format("No Crew found for ID %d", id), NOT_FOUND);
        }
        return ResponseEntity.
                ok(crewMapper.toDto(crew.get()));
    }


    @PutMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CrewDto> update(final @PathVariable("id") Long id,
                                             @RequestBody CrewDto crewDto) {
        crewDto = crewService.update(id, crewDto);
        return ResponseEntity.
                status(HttpStatus.ACCEPTED).body(crewDto);
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable("id") Long id ) {
        if(!crewService.findById(id).isPresent()){
            return new ResponseEntity<>
                    (String.format("No Crew found for ID %d", id), NOT_FOUND);
        }
        crewService.deleteById(id);

        return ResponseEntity.
                status(HttpStatus.ACCEPTED).build();
    }
}


package com.logistic.task.controller;

import com.logistic.task.dto.ShiftDto;
import com.logistic.task.dto.VehicleDto;
import com.logistic.task.entity.Shift;
import com.logistic.task.entity.Vehicle;
import com.logistic.task.mapper.ShiftMapper;
import com.logistic.task.mapper.VehicleMapper;
import com.logistic.task.service.ShiftService;
import com.logistic.task.service.VehicleService;
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
@RequestMapping("/vehicle")
public class VehicleController {



    private final VehicleService service;
    private final VehicleMapper mapper;

    @GetMapping
            (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleDto>> findAll() {
        return ResponseEntity.
                ok(mapper.toDto(service.findAll()));
    }

    @PostMapping
            (produces = MediaType.
                    APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid VehicleDto vehicleDto) {
        vehicleDto = service.save(vehicleDto);
        return ResponseEntity.
                status(HttpStatus.CREATED).body(vehicleDto);
    }


    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        Optional<Vehicle> shift = service.findById(id);
        if (!shift.isPresent()) {
            return new ResponseEntity<>
                    (format("No Vehicle found for ID %d", id), NOT_FOUND);
        }
        return ResponseEntity.
                ok(mapper.toDto(shift.get()));
    }

    @PutMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<@Valid VehicleDto> update(@PathVariable("id") Long id,
                                                  @RequestBody @Valid VehicleDto vehicleDto) {
        vehicleDto = service.update(id, vehicleDto);
        return ResponseEntity.
                status(HttpStatus.ACCEPTED).body(vehicleDto);
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        if(!service.findById(id).isPresent()){
            return new ResponseEntity<>
                    (format("No Vehicle found for ID %d", id), NOT_FOUND);
        }
        service.deleteById(id);

        return ResponseEntity.
                status(HttpStatus.ACCEPTED).build();
    }

}

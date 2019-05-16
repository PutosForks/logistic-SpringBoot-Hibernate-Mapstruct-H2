package com.logistic.task.service;

import com.logistic.task.dto.FlightDto;
import com.logistic.task.entity.Address;
import com.logistic.task.entity.Flight;
import com.logistic.task.mapper.AddressMapper;
import com.logistic.task.mapper.FlightMapper;
import com.logistic.task.repository.AddressRepository;
import com.logistic.task.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 16.05.2019
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository repository;
    private final FlightMapper mapper;


    @Override
    @Transactional
    public List<Flight> findAll() {
        log.info("Getting all flight");
        return repository.findAll();
    }

    @Override
    @Transactional
    public Optional<Flight> findById(Long id) {
        log.info("Getting flight by id {}", id);
        return repository.findById(id);
    }

    @Override
    public FlightDto save(FlightDto save) {
        log.info("Saving flight {}", save);
        Flight flight = repository.save(mapper.toEntity(save));
        return mapper.toDto(flight);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Flight> flight = repository.findById(id);
        if (!flight.isPresent()) {
            log.debug("Can't delete flight. Flight doesn't exist {}", id);
            return false;
        }
        log.info("Deleting flight {}", flight.get());
        repository.delete(flight.get());
        return true;
    }

    @Override
    @Transactional
    public FlightDto update(Long id, FlightDto flightDto) {
        Flight source = mapper.toEntity(flightDto);
        log.info("Updating flight {}", source);
        Flight target = repository.findById(id).get();
      /*  try {
            copyProperties(target, source);
        } catch (Exception e) {
            log.error("Can't get properties from object to updatable object for client", e);
        }*/
        target.setClients(source.getClients());
        target.setCrew(source.getCrew());
        target.setVehicle(source.getVehicle());
        target.setId(id);

        repository.save(target);
        return mapper.toDto(target);
    }
}

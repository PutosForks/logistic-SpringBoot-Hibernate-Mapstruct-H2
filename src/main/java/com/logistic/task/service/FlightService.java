package com.logistic.task.service;

import com.logistic.task.dto.FlightDto;
import com.logistic.task.entity.Flight;

import java.util.List;
import java.util.Optional;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 13.05.2019
 */

public interface FlightService extends BaseService<FlightDto, Flight> {
    @Override
    List<Flight> findAll();

    @Override
    Optional<Flight> findById(Long id);

    @Override
    FlightDto save(FlightDto save);

    @Override
    boolean deleteById(Long id);

    @Override
    FlightDto update(Long id, FlightDto flightDto);
}

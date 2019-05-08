package com.logistic.task.mapper;

import com.logistic.task.dto.FlightDto;
import com.logistic.task.entity.Flight;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Mapper
public interface FlightMapper extends BaseMapper<FlightDto, Flight> {
    @Override
    FlightDto toDto(Flight flight);

    @Override
    List<FlightDto> toDto(List<Flight> flights);

    @Override
    Flight toEntity(FlightDto flightDto);

    @Override
    List<Flight> toEntity(List<FlightDto> flightDtos);
}

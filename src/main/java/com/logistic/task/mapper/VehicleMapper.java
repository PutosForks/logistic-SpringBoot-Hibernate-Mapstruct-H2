package com.logistic.task.mapper;

import com.logistic.task.dto.VehicleDto;
import com.logistic.task.entity.Vehicle;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Mapper
public interface VehicleMapper extends BaseMapper<VehicleDto, Vehicle> {
    @Override
    VehicleDto toDto(Vehicle vehicle);

    @Override
    List<VehicleDto> toDto(List<Vehicle> vehicles);

    @Override
    Vehicle toEntity(VehicleDto vehicleDto);

    @Override
    List<Vehicle> toEntity(List<VehicleDto> vehicleDtos);
}

package com.logistic.task.service;

import com.logistic.task.dto.VehicleDto;
import com.logistic.task.entity.Vehicle;
import com.logistic.task.mapper.ShiftMapper;
import com.logistic.task.mapper.VehicleMapper;
import com.logistic.task.repository.ShiftRepository;
import com.logistic.task.repository.VehicleRepository;
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
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repository;
    private final VehicleMapper mapper;


    @Override
    @Transactional
    public List<Vehicle> findAll() {
        log.info("Getting all vehicle");
        return repository.findAll();
    }

    @Override
    @Transactional
    public Optional<Vehicle> findById(Long id) {
        log.info("Getting vehicle by id {}", id);
        return repository.findById(id);
    }

    @Override
    @Transactional
    public VehicleDto save(VehicleDto save) {
        log.info("Saving vehicle {}", save);
        Vehicle vehicle = repository.save(mapper.toEntity(save));
        return mapper.toDto(vehicle);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Vehicle> vehicle = repository.findById(id);
        if (!vehicle.isPresent()) {
            log.debug("Can't delete shift. Shift doesn't exist {}", id);
            return false;
        }
        log.info("Deleting person {}", vehicle.get());
        repository.delete(vehicle.get());
        return true;
    }

    @Override
    @Transactional
    public VehicleDto update(Long id, VehicleDto vehicleDto) {
        Vehicle source = mapper.toEntity(vehicleDto);
        log.info("Updating person {}", source);
        Vehicle target = repository.findById(id).get();
      /*  try {
            copyProperties(target, source);
        } catch (Exception e) {
            log.error("Can't get properties from object to updatable object for client", e);
        }*/
        target.setCarNumber(source.getCarNumber());
        target.setVehicleState(source.getVehicleState());
        target.setId(id);

        repository.save(target);
        return mapper.toDto(target);
    }
}

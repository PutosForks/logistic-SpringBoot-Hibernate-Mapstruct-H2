package com.logistic.task.repository;

import com.logistic.task.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}

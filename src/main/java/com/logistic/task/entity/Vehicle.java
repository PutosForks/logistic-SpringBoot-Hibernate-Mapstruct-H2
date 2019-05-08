package com.logistic.task.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Entity
@Data
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private VehicleState vehicleState;
    private String carNumber;

    public Vehicle(VehicleState vehicleState, String carNumber) {
        this.vehicleState = vehicleState;
        this.carNumber = carNumber;
    }
}

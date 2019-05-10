package com.logistic.task.dto;

import com.logistic.task.entity.VehicleState;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Data
public class VehicleDto {
    private long id;
    private VehicleState vehicleState;
    private String carNumber;

}

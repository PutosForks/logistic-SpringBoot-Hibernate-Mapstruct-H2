package com.logistic.task.dto;

import com.logistic.task.entity.Client;
import com.logistic.task.entity.Crew;
import com.logistic.task.entity.Vehicle;
import lombok.Data;

import java.util.List;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Data
public class FlightDto {
    private long id;

    private List<Client> clients;
    private Vehicle vehicle;
    private Crew crew;

}

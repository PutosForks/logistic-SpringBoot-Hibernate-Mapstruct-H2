package com.logistic.task.dto;

import com.logistic.task.entity.Address;
import com.logistic.task.entity.Shift;
import lombok.*;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Data
@Setter
@Getter

@ToString
@EqualsAndHashCode
public class PersonDto {
    private long id;
    private String name;
    private int phoneNumber;
    private Address address;
    private Shift shift;        // рабочая смена
}

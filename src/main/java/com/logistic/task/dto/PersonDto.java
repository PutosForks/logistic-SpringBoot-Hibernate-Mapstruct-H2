package com.logistic.task.dto;

import com.logistic.task.entity.Shift;
import lombok.*;

import java.util.List;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Data
public class PersonDto {
    private long id;
    private String name;
    private String phoneNumber;
    private List<Shift> shifts;        // рабочая смена
}

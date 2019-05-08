package com.logistic.task.dto;

import com.logistic.task.entity.Person;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Data
@Getter
@Setter
public class CrewDto {

    private long id;
    private Date date;
    private List<Person> crewList;
}

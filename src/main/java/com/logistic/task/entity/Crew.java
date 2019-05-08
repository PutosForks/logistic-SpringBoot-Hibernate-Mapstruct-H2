package com.logistic.task.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Entity
@Data
@NoArgsConstructor
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date date;
    @ElementCollection(targetClass=Person.class)
    @OneToMany(cascade=CascadeType.ALL)
    private List<Person> crewList;

    public Crew(Date date) {
        this.date = date;
    }
}

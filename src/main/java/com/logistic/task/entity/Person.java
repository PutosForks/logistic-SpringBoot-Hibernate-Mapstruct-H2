package com.logistic.task.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Entity
@Data
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String phoneNumber;
    @ManyToOne(targetEntity=Crew.class)
    @JoinColumn
    private Crew crew;
    @OneToMany(targetEntity=Shift.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Shift> shifts;        // рабочая смена

    public Person(String name, String phoneNumber, List<Shift> shifts) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.shifts = shifts;
    }
}

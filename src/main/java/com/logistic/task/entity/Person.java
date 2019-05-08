package com.logistic.task.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private int phoneNumber;
    @ManyToOne(cascade=CascadeType.ALL)
    private Address address;
    @ManyToOne(cascade=CascadeType.ALL)
    private Shift shift;        // рабочая смена

    public Person(String name, int phoneNumber, Address address, Shift shift) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.shift = shift;
    }
}

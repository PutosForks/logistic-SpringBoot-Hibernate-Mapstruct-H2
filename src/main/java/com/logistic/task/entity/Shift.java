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
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(targetEntity=Person.class)
    @JoinColumn
    private Person person;

    private String stubHealthChangeEtc; // заглушка состояние здоровья, смена и пр.\

    public Shift(String stubHealthChangeEtc) {
        this.stubHealthChangeEtc = stubHealthChangeEtc;
    }
}

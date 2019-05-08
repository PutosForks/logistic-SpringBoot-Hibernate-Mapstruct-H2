package com.logistic.task.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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

    private String stubHealthChangeEtc; // заглушка состояние здоровья, смена и пр.\

    public Shift(String stubHealthChangeEtc) {
        this.stubHealthChangeEtc = stubHealthChangeEtc;
    }
}

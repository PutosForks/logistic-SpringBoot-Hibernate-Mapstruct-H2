package com.logistic.task.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Entity
@Data
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Client> clients;
    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private Crew crew;


    public Flight(List<Client> clients, Vehicle vehicle, Crew crew) {
        this.clients = clients;
        this.vehicle = vehicle;
        this.crew = crew;
    }
}

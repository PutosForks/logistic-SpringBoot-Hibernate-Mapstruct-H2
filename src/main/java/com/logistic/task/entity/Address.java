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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String address; // заглушка
    @ManyToOne(targetEntity=Client.class)
    @JoinColumn
    private Client client;


    public Address(String address) {
        this.address = address;
    }

    public Address(String address, Client client) {
        this.address = address;
        this.client = client;
    }
}

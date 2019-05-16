package com.logistic.task.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 04.05.2019
 */
@Entity
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String phoneNumber;
    @OneToMany(targetEntity=Address.class,
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Address> address;
    @ManyToOne(targetEntity = Flight.class)
    @JoinColumn
    private Flight flight;

    public Client(String name, String phoneNumber,
                  List<Address> address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}

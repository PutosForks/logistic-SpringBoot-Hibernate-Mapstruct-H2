package com.logistic.task.repository;


import com.logistic.task.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 04.05.2019
 */

public interface ClientRepository extends JpaRepository<Client, Long> {

}

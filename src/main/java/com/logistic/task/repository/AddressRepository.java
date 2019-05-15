package com.logistic.task.repository;

import com.logistic.task.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */

public interface AddressRepository extends JpaRepository<Address, Long> {

}

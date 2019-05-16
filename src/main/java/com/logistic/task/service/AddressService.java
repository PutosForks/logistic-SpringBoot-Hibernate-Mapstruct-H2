package com.logistic.task.service;

import com.logistic.task.dto.AddressDto;
import com.logistic.task.entity.Address;

import java.util.List;
import java.util.Optional;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 12.05.2019
 */

public interface AddressService extends BaseService<AddressDto,Address> {

    List<Address> findAll();

    Optional<Address> findById(Long id);

    AddressDto save(AddressDto addressDto);

    boolean deleteById(Long id);

    AddressDto update(Long id, AddressDto addressDto);



}

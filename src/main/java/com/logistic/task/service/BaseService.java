package com.logistic.task.service;

import java.util.List;
import java.util.Optional;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 15.05.2019
 */

public interface BaseService <DTO, ENTITY>{

    List<ENTITY> findAll();

    Optional<ENTITY> findById(Long id);

    DTO save(DTO save);

    boolean deleteById(Long id);

    DTO update(Long id, DTO dto);

}

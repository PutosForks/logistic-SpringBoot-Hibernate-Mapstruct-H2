package com.logistic.task.service;

import com.logistic.task.dto.CrewDto;
import com.logistic.task.entity.Crew;

import java.util.List;
import java.util.Optional;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 13.05.2019
 */

public interface CrewService extends BaseService<CrewDto, Crew>{
    @Override
    List<Crew> findAll();

    @Override
    Optional<Crew> findById(Long id);

    @Override
    CrewDto save(CrewDto save);

    @Override
    boolean deleteById(Long id);

    @Override
    CrewDto update(Long id, CrewDto crewDto);
}

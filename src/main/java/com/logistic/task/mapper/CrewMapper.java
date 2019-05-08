package com.logistic.task.mapper;

import com.logistic.task.dto.CrewDto;
import com.logistic.task.entity.Crew;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Mapper
public interface CrewMapper extends BaseMapper<CrewDto, Crew> {

    @Override
    CrewDto toDto(Crew crew);

    @Override
    List<CrewDto> toDto(List<Crew> crews);

    @Override
    Crew toEntity(CrewDto crewDto);

    @Override
    List<Crew> toEntity(List<CrewDto> crewDtos);
}

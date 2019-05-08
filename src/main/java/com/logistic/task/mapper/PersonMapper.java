package com.logistic.task.mapper;

import com.logistic.task.dto.PersonDto;
import com.logistic.task.entity.Person;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Mapper
public interface PersonMapper extends BaseMapper<PersonDto, Person> {
    @Override
    PersonDto toDto(Person person);

    @Override
    List<PersonDto> toDto(List<Person> people);

    @Override
    Person toEntity(PersonDto personDto);

    @Override
    List<Person> toEntity(List<PersonDto> personDtos);
}

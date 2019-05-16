package com.logistic.task.service;

import com.logistic.task.dto.PersonDto;
import com.logistic.task.entity.Person;
import com.logistic.task.mapper.FlightMapper;
import com.logistic.task.mapper.PersonMapper;
import com.logistic.task.repository.FlightRepository;
import com.logistic.task.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 16.05.2019
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;


    @Override
    @Transactional
    public List<Person> findAll() {
        log.info("Getting all person");
        return repository.findAll();
    }

    @Override
    public Optional<Person> findById(Long id) {
        log.info("Getting person by id {}", id);
        return repository.findById(id);
    }

    @Override
    @Transactional
    public PersonDto save(PersonDto save) {
        log.info("Saving person {}", save);
        Person person = repository.save(mapper.toEntity(save));
        return mapper.toDto(person);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Person> person = repository.findById(id);
        if (!person.isPresent()) {
            log.debug("Can't delete person. Person doesn't exist {}", id);
            return false;
        }
        log.info("Deleting person {}", person.get());
        repository.delete(person.get());
        return true;
    }

    @Override
    @Transactional
    public PersonDto update(Long id, PersonDto personDto) {
        Person source = mapper.toEntity(personDto);
        log.info("Updating person {}", source);
        Person target = repository.findById(id).get();
      /*  try {
            copyProperties(target, source);
        } catch (Exception e) {
            log.error("Can't get properties from object to updatable object for client", e);
        }*/
      target.setShifts(source.getShifts());
      target.setName(source.getName());
      target.setPhoneNumber(source.getPhoneNumber());
      target.setCrew(source.getCrew());
        target.setId(id);

        repository.save(target);
        return mapper.toDto(target);
    }
}

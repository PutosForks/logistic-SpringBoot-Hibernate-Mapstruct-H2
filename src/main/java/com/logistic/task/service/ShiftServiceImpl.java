package com.logistic.task.service;

import com.logistic.task.dto.ShiftDto;
import com.logistic.task.entity.Shift;
import com.logistic.task.mapper.PersonMapper;
import com.logistic.task.mapper.ShiftMapper;
import com.logistic.task.repository.PersonRepository;
import com.logistic.task.repository.ShiftRepository;
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
public class ShiftServiceImpl implements ShiftService {

    private final ShiftRepository repository;
    private final ShiftMapper mapper;


    @Override
    @Transactional
    public List<Shift> findAll() {
        log.info("Getting all shift");
        return repository.findAll();
    }

    @Override
    @Transactional
    public Optional<Shift> findById(Long id) {
        log.info("Getting shift by id {}", id);
        return repository.findById(id);
    }

    @Override
    @Transactional
    public ShiftDto save(ShiftDto save) {
        log.info("Saving person {}", save);
        Shift shift = repository.save(mapper.toEntity(save));
        return mapper.toDto(shift);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Shift> shift = repository.findById(id);
        if (!shift.isPresent()) {
            log.debug("Can't delete shift. Shift doesn't exist {}", id);
            return false;
        }
        log.info("Deleting person {}", shift.get());
        repository.delete(shift.get());
        return true;
    }

    @Override
    @Transactional
    public ShiftDto update(Long id, ShiftDto shiftDto) {
        Shift source = mapper.toEntity(shiftDto);
        log.info("Updating person {}", source);
        Shift target = repository.findById(id).get();
      /*  try {
            copyProperties(target, source);
        } catch (Exception e) {
            log.error("Can't get properties from object to updatable object for client", e);
        }*/
       target.setStubHealthChangeEtc(source.getStubHealthChangeEtc());
       target.setPerson(source.getPerson());
        target.setId(id);

        repository.save(target);
        return mapper.toDto(target);
    }
}

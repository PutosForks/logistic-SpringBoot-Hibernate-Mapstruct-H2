package com.logistic.task.service;

import com.logistic.task.dto.CrewDto;
import com.logistic.task.entity.Crew;
import com.logistic.task.mapper.CrewMapper;
import com.logistic.task.repository.CrewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


 /**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 15.05.2019
 */
@Slf4j
@RequiredArgsConstructor

@Service
public class CrewServiceImpl implements CrewService {
    private final CrewRepository repository;
    private final CrewMapper mapper;

    @Override
    @Transactional
    public List<Crew> findAll() {
        log.info("Getting all crew");
        return repository.findAll();
    }

    @Override
    @Transactional
    public Optional<Crew> findById(Long id) {
        log.info("Getting crew by id {}", id);
        return repository.findById(id);
    }

    @Override
    @Transactional
    public CrewDto save(CrewDto dto) {
        log.info("Saving crew {}", dto);
        Crew entity = repository.save(mapper.toEntity(dto));
        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Crew> crew = repository.findById(id);
        if (!crew.isPresent()) {
            log.debug("Can't delete crew. Crew doesn't exist {}", id);
            return false;
        }
        log.info("Deleting crew {}", crew.get());
        repository.delete(crew.get());
        return true;

    }

    @Override
    @Transactional
    public CrewDto update(Long id, CrewDto  dto) {
        Crew source = mapper.toEntity(dto);
        log.info("Updating crew {}", source);
        Crew target = repository.findById(id).get();
       /* try {
            copyProperties(target, source);
        } catch (Exception e) {
            log.error("Can't get properties from object to updatable object for crew", e);
        }*/
       target.setCrewList(source.getCrewList());
       target.setDate(source.getDate());
       target.setId(id);

        repository.save(target);
        return mapper.toDto(target);
    }


}

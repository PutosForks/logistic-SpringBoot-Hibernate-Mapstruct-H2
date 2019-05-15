package com.logistic.task.service;

import com.logistic.task.dto.AddressDto;
import com.logistic.task.entity.Address;
import com.logistic.task.mapper.AddressMapper;
import com.logistic.task.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    @Transactional
    public List<Address> findAll() {
        log.info("Getting all addresses");
        return addressRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Address> findById(Long id) {
        log.info("Getting address by id {}", id);
        return addressRepository.findById(id);
    }

    @Override
    @Transactional
    public AddressDto save(AddressDto addressDto) {
        log.info("Saving address {}", addressDto);
        Address address = addressRepository.save(addressMapper.toEntity(addressDto));
        return addressMapper.toDto(address);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent()) {
            log.debug("Can't delete address. Address doesn't exist {}", id);
            return false;
        }
        log.info("Deleting address {}", address.get());
        addressRepository.delete(address.get());
        return true;

    }

    @Override
    public AddressDto update(Long id, AddressDto addressDto) {
        Address source = addressMapper.toEntity(addressDto);
        log.info("Updating address {}", source);
        Address target = addressRepository.findById(id).get();
      /*  try {
            copyProperties(target, source);
        } catch (Exception e) {
            log.error("Can't get properties from object to updatable object for client", e);
        }*/
        target.setAddress(source.getAddress());
        target.setClient(source.getClient());
        target.setId(id);

        addressRepository.save(target);
        return addressMapper.toDto(target);
    }


}

package com.logistic.task.mapper;

import com.logistic.task.dto.AddressDto;
import com.logistic.task.entity.Address;
import com.logistic.task.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Mapper
public interface AddressMapper extends BaseMapper<AddressDto, Address> {
    @Override

    AddressDto toDto(Address address);

    @Override
    List<AddressDto> toDto(List<Address> addresses);

    @Override
    Address toEntity(AddressDto addressDto);

    @Override
    List<Address> toEntity(List<AddressDto> addressDtos);

    default Client createClientById(long id) {
        Client client = new Client();
        client.setId(id);
        return client;
    }
}

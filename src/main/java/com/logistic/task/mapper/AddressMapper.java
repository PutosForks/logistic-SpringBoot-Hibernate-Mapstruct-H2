package com.logistic.task.mapper;

import com.logistic.task.dto.AddressDto;
import com.logistic.task.entity.Address;
import org.mapstruct.Mapper;

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
}

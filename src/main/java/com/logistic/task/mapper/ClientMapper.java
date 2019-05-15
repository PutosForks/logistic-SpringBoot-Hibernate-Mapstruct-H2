package com.logistic.task.mapper;


import com.logistic.task.dto.ClientDto;
import com.logistic.task.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(uses = AddressMapper.class)
public interface ClientMapper extends BaseMapper<ClientDto, Client> {


    @Override
    ClientDto toDto(Client client);

    @Override
    List<ClientDto> toDto(List<Client> clients);

    @Override
    Client toEntity(ClientDto clientDto);

    @Override
    List<Client> toEntity(List<ClientDto> clientDtos);
}

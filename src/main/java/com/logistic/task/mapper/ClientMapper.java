package com.logistic.task.mapper;


import com.logistic.task.dto.ClientDto;
import com.logistic.task.entity.Client;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper
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

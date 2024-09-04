package com.williamsaran.cadastroservice.util;

import com.williamsaran.cadastroservice.model.Cliente;
import com.williamsaran.cadastroservice.model.dto.ClienteDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.FIELD)
public interface ClienteMapper {
    @Mapping(target = "id")
    ClienteDTO clienteToClienteDto(Cliente cliente);

    @Mapping(target = "id")
    Cliente clienteDtoToCliente(ClienteDTO clienteDto);
}
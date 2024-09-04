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
    @Mapping(source = "id", target = "id")
    ClienteDTO converter(Cliente cliente);

    @Mapping(source = "id", target = "id")
    Cliente converter(ClienteDTO clienteDto);
}
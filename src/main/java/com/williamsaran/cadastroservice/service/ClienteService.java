package com.williamsaran.cadastroservice.service;

import com.williamsaran.cadastroservice.exception.ClienteNaoEncontradoException;
import com.williamsaran.cadastroservice.model.Cliente;
import com.williamsaran.cadastroservice.model.dto.ClienteDTO;
import com.williamsaran.cadastroservice.repository.ClienteRepository;
import com.williamsaran.cadastroservice.util.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ClienteService {
    private static final Logger LOGGER = Logger.getLogger(ClienteService.class.getName());

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteMapper mapper;

    public ClienteDTO encontrarPorId(Long id) {
        LOGGER.info("Buscando cliente por ID: " + id);
        var cliente = repository
                .findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(LOGGER));

        return mapper.converter(cliente);
    }

    public ClienteDTO criarConta(ClienteDTO dto) {
        Cliente cliente = mapper.converter(dto);
        cliente.setId(null);

        return mapper.converter(
                repository.save(cliente)
        );
    }

    public ClienteDTO atualizarConta(ClienteDTO dto) {
        LOGGER.info("Atualizando cliente: " + dto.getId());

        Cliente cliente = repository.findById(dto.getId())
                .orElseThrow(() -> new ClienteNaoEncontradoException(LOGGER));

        return mapper.converter(
                repository.save(cliente)
        );
    }

    public void deletarPorId(Long id) {
        LOGGER.info("Deletando cliente: " + id);

        Cliente cliente = repository
                .findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(LOGGER));

        repository.delete(cliente);
    }

    public ClienteDTO desativarConta(Long id) {
        LOGGER.info("Desativando cliente: " + id);

        var cliente = repository
                .findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(LOGGER));

        cliente.desativar();

        return mapper.converter(
                repository.save(cliente)
        );
    }
}

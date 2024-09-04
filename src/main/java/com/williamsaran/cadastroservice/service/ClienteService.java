package com.williamsaran.cadastroservice.service;

import com.williamsaran.cadastroservice.model.Cliente;
import com.williamsaran.cadastroservice.model.dto.ClienteDTO;
import com.williamsaran.cadastroservice.repository.ClienteRepository;
import com.williamsaran.cadastroservice.util.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteMapper mapper;

    public ClienteDTO encontrarPorId(Long id) {
        var cliente = repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

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
        Cliente cliente = repository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        return mapper.converter(
                repository.save(cliente)
        );
    }

    public void deletarPorId(Long id) {
        Cliente cliente = repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        repository.delete(cliente);
    }

    public ClienteDTO desativarConta(Long id) {
        var cliente = repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        cliente.desativar();

        return mapper.converter(
                repository.save(cliente)
        );
    }
}

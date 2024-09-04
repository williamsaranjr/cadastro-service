package com.williamsaran.cadastroservice.service;

import com.williamsaran.cadastroservice.model.Cliente;
import com.williamsaran.cadastroservice.model.dto.ClienteDTO;
import com.williamsaran.cadastroservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public ClienteDTO encontrarPorId(Long id) {
        var cliente = repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        return cliente.converter();
    }

    public ClienteDTO criarConta(ClienteDTO clienteDTO) {
        Cliente cliente = clienteDTO.converter();

        return repository
                .save(cliente)
                .converter();
    }

    public void deletarPorId(Long id) {
        Cliente cliente = repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        repository.delete(cliente);
    }
}

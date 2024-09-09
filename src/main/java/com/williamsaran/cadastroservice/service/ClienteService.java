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
        var cliente = buscarClientePorId(id);

        return mapper.converter(cliente);
    }

    public ClienteDTO criarConta(ClienteDTO dto) {
        Cliente cliente = mapper.converter(dto);
        cliente.setId(null);

        return mapper.converter(
                repository.save(cliente)
        );
    }

    // TODO: Finalizar a implementação da atualização parcial de conta

    public ClienteDTO atualizarConta(ClienteDTO dto) {
        LOGGER.info("Atualizando cliente: " + dto.getId());

        Cliente cliente = buscarClientePorId(dto.getId());

        cliente.atualizarParcialmente(dto);

        return mapper.converter(
                repository.save(cliente)
        );
    }

    public void deletarPorId(Long id) {
        LOGGER.info("Deletando cliente: " + id);

        Cliente cliente = buscarClientePorId(id);

        repository.delete(cliente);
    }

    public ClienteDTO desativarConta(Long id) {
        LOGGER.info("Desativando cliente: " + id);

        var cliente = buscarClientePorId(id);

        cliente.desativar();

        return mapper.converter(
                repository.save(cliente)
        );
    }

    /**
     * Função auxiliar para buscar um cliente por ID
     *
     * @param id ID a ser consultado
     * @return Cliente correspondente ao ID
     *
     * @throws ClienteNaoEncontradoException Caso o cliente não seja encontrado
     */
    private Cliente buscarClientePorId(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(LOGGER));
    }
}

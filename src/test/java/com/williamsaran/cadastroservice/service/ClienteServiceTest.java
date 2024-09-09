package com.williamsaran.cadastroservice.service;

import com.williamsaran.cadastroservice.exception.ClienteNaoEncontradoException;
import com.williamsaran.cadastroservice.exception.ContaDesativadaException;
import com.williamsaran.cadastroservice.model.Cliente;
import com.williamsaran.cadastroservice.repository.ClienteRepository;
import com.williamsaran.cadastroservice.util.ClienteMapper;
import com.williamsaran.cadastroservice.util.ClienteUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ClienteServiceTest {
    @Spy
    private ClienteMapper mapper = Mappers.getMapper(ClienteMapper.class);
    @Mock
    private ClienteRepository repository;
    @InjectMocks
    private ClienteService service;

    @Test
    public void testEncontrarPorIdValido() {
        buscarContaValida();

        var cliente = service.encontrarPorId(1L);

        Assertions.assertEquals(ClienteUtil.ID, cliente.getId());
        Assertions.assertEquals(ClienteUtil.NOME, cliente.getNome());
    }

    @Test
    public void testEncontrarPorIdInvalido() {
        buscarContaInvalida();

        Assertions.assertThrows(
                ClienteNaoEncontradoException.class,
                () -> service.encontrarPorId(1L)
        );
    }

    @Test
    public void testCriarConta() {
        salvarContaValida();

        var entity = service.criarConta(ClienteUtil.criarDTOMock());

        Assertions.assertEquals(ClienteUtil.ID, entity.getId());
        Assertions.assertEquals(ClienteUtil.NOME, entity.getNome());
    }

    @Test
    public void testAtualizarContaInvalida() {
        buscarContaInvalida();

        Assertions.assertThrows(
                ClienteNaoEncontradoException.class,
                () -> service.atualizarConta(ClienteUtil.criarDTOMock())
        );
    }

    // TODO: implementar testes de atualização parcial de conta

    @Test
    public void testDeletarContaInvalida() {
        buscarContaInvalida();

        Assertions.assertThrows(
                ClienteNaoEncontradoException.class,
                () -> service.deletarPorId(1L)
        );
    }

    @Test
    public void testDeletarContaValida() {
        Cliente cliente = ClienteUtil.criarClienteMock();

        Mockito.when(
                repository.findById(ArgumentMatchers.anyLong())
        ).thenReturn(Optional.of(cliente));

        service.deletarPorId(1L);

        Mockito.verify(repository, Mockito.times(1)).delete(cliente);
    }

    @Test
    public void testDesativarContaInvalida() {
        buscarContaInvalida();

        Assertions.assertThrows(
                ClienteNaoEncontradoException.class,
                () -> service.desativarConta(1L)
        );
    }

    @Test
    public void testDesativarContaDesativada() {
        Cliente cliente = ClienteUtil.criarClienteMock();
        cliente.setCorrentista(false);

        Mockito.when(
          repository.findById(ArgumentMatchers.anyLong())
        ).thenReturn(Optional.of(cliente));

        Assertions.assertThrows(
                ContaDesativadaException.class,
                () -> service.desativarConta(1L)
        );
    }

    /**
     * Instrui o Mock do {@link ClienteRepository} a retornar uma conta válida
     * ao salvar uma nova conta.
     */
    private Cliente salvarContaValida() {
        Cliente cliente = ClienteUtil.criarClienteMock();

        Mockito.when(repository.save(ArgumentMatchers.any()))
                .thenReturn(cliente);

        return cliente;
    }

    /**
     * Instrui o Mock do {@link ClienteRepository} a retornar uma conta válida
     * ao realizar uma busca por ID.
     */
    private Cliente buscarContaValida() {
        Cliente cliente = ClienteUtil.criarClienteMock();

        Mockito.when(repository.findById(1L))
                .thenReturn(Optional.of(cliente));

        return cliente;
    }

    /**
     * Instrui o Mock do {@link ClienteRepository} a retornar uma conta inválida
     * ao realizar uma busca por ID.
     *
     * @return Um {@link Optional} de cliente vazio
     */
    private Optional<Cliente> buscarContaInvalida() {
        Optional<Cliente> empty = Optional.empty();

        Mockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(empty);

        return empty;
    }
}

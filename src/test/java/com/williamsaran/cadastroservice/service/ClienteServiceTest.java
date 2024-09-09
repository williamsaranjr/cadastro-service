package com.williamsaran.cadastroservice.service;

import com.williamsaran.cadastroservice.exception.ClienteNaoEncontradoException;
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
        Mockito.when(
                repository.findById(1L)
                ).thenReturn(Optional.of(ClienteUtil.criarClienteMock()));

        var cliente = service.encontrarPorId(1L);

        Assertions.assertEquals(ClienteUtil.ID, cliente.getId());
        Assertions.assertEquals(ClienteUtil.NOME, cliente.getNome());
    }

    @Test
    public void testEncontrarPorIdInvalido() {
        Mockito.when(
                repository.findById(ArgumentMatchers.anyLong())
        ).thenReturn(Optional.empty());

        Assertions.assertThrows(
                ClienteNaoEncontradoException.class,
                () -> service.encontrarPorId(1L)
        );
    }

    @Test
    public void testCriarConta() {
        Mockito.when(
                repository.save(ArgumentMatchers.any())
        ).thenReturn(ClienteUtil.criarClienteMock());

        var entity = service.criarConta(ClienteUtil.criarDTOMock());

        Assertions.assertEquals(ClienteUtil.ID, entity.getId());
        Assertions.assertEquals(ClienteUtil.NOME, entity.getNome());
    }
}

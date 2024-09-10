package com.williamsaran.cadastroservice.controller;

import com.google.gson.Gson;
import com.williamsaran.cadastroservice.exception.ClienteNaoEncontradoException;
import com.williamsaran.cadastroservice.exception.ContaDesativadaException;
import com.williamsaran.cadastroservice.model.dto.ClienteDTO;
import com.williamsaran.cadastroservice.service.ClienteService;
import com.williamsaran.cadastroservice.util.ClienteUtil;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class ClienteControllerTest {
    @Autowired
    private MockMvc mock;
    @MockBean
    private ClienteService service;
    @InjectMocks
    private ClienteController controller;

    private static final Gson gson = new Gson();

    @Test
    public void testEncontrarPorId() throws Exception {
        when(service.encontrarPorId(ArgumentMatchers.anyLong()))
                .thenReturn(ClienteUtil.criarDTOMock());

        mock.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ClienteUtil.ID))
                .andExpect(jsonPath("$.nome").value(ClienteUtil.NOME));
    }

    @Test
    public void testEncontrarPorIdInvalido() throws Exception {
        when(service.encontrarPorId(ArgumentMatchers.anyLong()))
                .thenThrow(new ClienteNaoEncontradoException());

        mock.perform(get("/clientes/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensagem").value(ClienteNaoEncontradoException.MSG));
    }

    @Test
    public void testCriarConta() throws Exception {
        var cliente = ClienteUtil.criarDTOMock();

        when(service.criarConta(ArgumentMatchers.any()))
                .thenReturn(cliente);

        mock.perform(post("/clientes/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(gson.toJson(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ClienteUtil.ID))
                .andExpect(jsonPath("$.nome").value(ClienteUtil.NOME));
    }

    @Test
    public void testAtualizarConta() throws Exception {
        ClienteDTO cliente = ClienteUtil.criarDTOMock();

        when(service.atualizarConta(ArgumentMatchers.any()))
                .thenReturn(cliente);

        mock.perform(patch("/clientes/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(gson.toJson(cliente)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDesativarConta() throws Exception {
        var cliente = ClienteUtil.criarDTOMock();
        cliente.setCorrentista(false);

        when(service.desativarConta(ArgumentMatchers.anyLong()))
                .thenReturn(cliente);

        mock.perform(patch("/clientes/desativar/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correntista").value(false));
    }

    @Test
    public void testDesativarContaDesativada() throws Exception {
        when(service.desativarConta(ArgumentMatchers.anyLong()))
                .thenThrow(new ContaDesativadaException());

        mock.perform(patch("/clientes/desativar/1"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.mensagem").value(ContaDesativadaException.MSG));
    }

    @Test
    public void testDeletarPorId() throws Exception {
        doNothing()
                .when(service)
                .deletarPorId(ArgumentMatchers.anyLong());

        mock.perform(delete("/clientes/1"))
                .andExpect(status().isNoContent());
    }
}

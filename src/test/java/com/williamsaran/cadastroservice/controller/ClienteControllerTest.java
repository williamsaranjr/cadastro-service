package com.williamsaran.cadastroservice.controller;

import com.google.gson.Gson;
import com.williamsaran.cadastroservice.model.dto.ClienteDTO;
import com.williamsaran.cadastroservice.service.ClienteService;
import com.williamsaran.cadastroservice.util.ClienteUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
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

    private ClienteDTO dto;

    @BeforeEach
    public void setUp() {
        dto = ClienteUtil.criarDTOMock();
    }

    @Test
    public void testEncontrarPorId() throws Exception {
        when(service.encontrarPorId(1L)).thenReturn(dto);

        mock.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ClienteUtil.ID))
                .andExpect(jsonPath("$.nome").value(ClienteUtil.NOME))
                .andExpect(jsonPath("$.telefone").value(ClienteUtil.TELEFONE))
                .andExpect(jsonPath("$.correntista").value(ClienteUtil.CORRENTISTA))
                .andExpect(jsonPath("$.scoreCredito").value(ClienteUtil.SCORE_CREDITO))
                .andExpect(jsonPath("$.saldo").value(ClienteUtil.SALDO));
    }

    @Test
    public void testDesativarConta() throws Exception {
        dto.setCorrentista(false);

        when(service.desativarConta(1L)).thenReturn((dto));

        mock.perform(put("/clientes/desativar/1"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.id").value(ClienteUtil.ID))
                .andExpect(jsonPath("$.nome").value(ClienteUtil.NOME))
                .andExpect(jsonPath("$.telefone").value(ClienteUtil.TELEFONE))
                .andExpect(jsonPath("$.correntista").value(false))
                .andExpect(jsonPath("$.scoreCredito").value(ClienteUtil.SCORE_CREDITO))
                .andExpect(jsonPath("$.saldo").value(ClienteUtil.SALDO));
    }

    @Test
    public void testDeletarConta() throws Exception {
        doNothing().when(service).deletarPorId(1L);

        mock.perform(delete("/clientes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAtualizarConta() throws Exception {
        Gson gson = new Gson();

        when(service.atualizarConta(ArgumentMatchers.any())).thenReturn(dto);

        var response = mock.perform(patch("/clientes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var responseCliente = gson.fromJson(response, ClienteDTO.class);

        Assertions.assertEquals(responseCliente.getId(), dto.getId());
    }
}

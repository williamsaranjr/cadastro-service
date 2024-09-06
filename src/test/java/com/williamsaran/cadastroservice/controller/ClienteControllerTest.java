package com.williamsaran.cadastroservice.controller;

import com.google.gson.Gson;
import com.williamsaran.cadastroservice.model.dto.ClienteDTO;
import com.williamsaran.cadastroservice.service.ClienteService;
import com.williamsaran.cadastroservice.util.ClienteMock;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@NoArgsConstructor
@AutoConfigureMockMvc
@SpringBootTest
public class ClienteControllerTest {
    @Autowired
    private MockMvc mock;

    @MockBean
    private ClienteService service;

    @Autowired
    private Gson gson;

    private ClienteDTO dto;

    @BeforeEach
    public void setUp() {
        dto = ClienteMock.criarClienteDTOMock();
    }

    @Test
    public void testEncontrarPorId() throws Exception {
        when(service.encontrarPorId(1L)).thenReturn(dto);

        mock.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ClienteMock.ID))
                .andExpect(jsonPath("$.nome").value(ClienteMock.NOME))
                .andExpect(jsonPath("$.telefone").value(ClienteMock.TELEFONE))
                .andExpect(jsonPath("$.correntista").value(ClienteMock.CORRENTISTA))
                .andExpect(jsonPath("$.scoreCredito").value(ClienteMock.SCORE_CREDITO))
                .andExpect(jsonPath("$.saldo").value(ClienteMock.SALDO));
    }

    @Test
    public void testDesativarConta() throws Exception {
        dto.setCorrentista(false);

        when(service.desativarConta(1L)).thenReturn((dto));

        mock.perform(patch("/clientes/desativar/1"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.id").value(ClienteMock.ID))
                .andExpect(jsonPath("$.nome").value(ClienteMock.NOME))
                .andExpect(jsonPath("$.telefone").value(ClienteMock.TELEFONE))
                .andExpect(jsonPath("$.correntista").value(false))
                .andExpect(jsonPath("$.scoreCredito").value(ClienteMock.SCORE_CREDITO))
                .andExpect(jsonPath("$.saldo").value(ClienteMock.SALDO));
    }

//    @Test
//    public void testAtualizarConta() throws Exception {
//        when(service.atualizarConta(dto)).thenReturn(dto);
//
//        mock.perform(patch("/clientes/")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(gson.toJson(dto))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(ClienteMock.ID))
//                .andExpect(jsonPath("$.nome").value(ClienteMock.NOME))
//                .andExpect(jsonPath("$.telefone").value(ClienteMock.TELEFONE))
//                .andExpect(jsonPath("$.correntista").value(ClienteMock.CORRENTISTA))
//                .andExpect(jsonPath("$.scoreCredito").value(ClienteMock.SCORE_CREDITO))
//                .andExpect(jsonPath("$.saldo").value(ClienteMock.SALDO));
//    }
}

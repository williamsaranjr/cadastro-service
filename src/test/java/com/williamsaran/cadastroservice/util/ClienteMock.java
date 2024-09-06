package com.williamsaran.cadastroservice.util;

import com.williamsaran.cadastroservice.model.dto.ClienteDTO;

public final class ClienteMock {
    public static final Long ID = 1L;
    public static final String NOME = "Fulano";
    public static final String TELEFONE = "11999999999";
    public static final Boolean CORRENTISTA = true;
    public static final Float SCORE_CREDITO = 800F;
    public static final Float SALDO = 1000F;
    
    public static ClienteDTO criarClienteDTOMock() {
        ClienteDTO cliente = new ClienteDTO();

        cliente.setId(1L);
        cliente.setNome("Fulano");
        cliente.setTelefone("11999999999");
        cliente.setCorrentista(true);
        cliente.setScoreCredito(800F);
        cliente.setSaldo(1000F);

        return cliente;
    }
}

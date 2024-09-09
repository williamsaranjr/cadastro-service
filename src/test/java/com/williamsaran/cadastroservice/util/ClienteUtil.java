package com.williamsaran.cadastroservice.util;

import com.williamsaran.cadastroservice.model.Cliente;
import com.williamsaran.cadastroservice.model.dto.ClienteDTO;

public final class ClienteUtil {
    public static final Long ID = 1L;
    public static final String NOME = "Fulano";
    public static final String TELEFONE = "11999999999";
    public static final Boolean CORRENTISTA = true;
    public static final Float SCORE_CREDITO = 800F;
    public static final Float SALDO = 1000F;
    
    public static ClienteDTO criarDTOMock() {
        ClienteDTO cliente = new ClienteDTO();

        cliente.setId(ID);
        cliente.setNome(NOME);
        cliente.setTelefone(TELEFONE);
        cliente.setCorrentista(CORRENTISTA);
        cliente.setScoreCredito(SCORE_CREDITO);
        cliente.setSaldo(SALDO);

        return cliente;
    }

    public static Cliente criarClienteMock() {
        Cliente cliente = new Cliente();

        cliente.setId(ID);
        cliente.setNome(NOME);
        cliente.setTelefone(TELEFONE);
        cliente.setCorrentista(CORRENTISTA);
        cliente.setScoreCredito(SCORE_CREDITO);
        cliente.setSaldo(SALDO);

        return cliente;
    }
}

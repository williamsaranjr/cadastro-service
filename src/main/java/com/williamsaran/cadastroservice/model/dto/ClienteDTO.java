package com.williamsaran.cadastroservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.williamsaran.cadastroservice.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    @JsonIgnore
    private Long id;
    private String nome;
    private String telefone;
    private Boolean correntista;
    private Float scoreCredito;
    private Float saldo;

    public Cliente converter() {
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setTelefone(this.telefone);
        cliente.setCorrentista(this.correntista);
        cliente.setScoreCredito(this.scoreCredito);
        cliente.setSaldo(this.saldo);
        return cliente;
    }
}

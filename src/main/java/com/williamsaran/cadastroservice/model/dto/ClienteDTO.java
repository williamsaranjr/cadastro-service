package com.williamsaran.cadastroservice.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String nome;
    private String telefone;
    private Boolean correntista;
    private Float scoreCredito;
    private Float saldo;
}

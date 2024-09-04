package com.williamsaran.cadastroservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
}

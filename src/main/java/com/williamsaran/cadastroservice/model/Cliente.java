package com.williamsaran.cadastroservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String telefone;

    @Column
    private Boolean correntista;

    @Column(name = "score_credito")
    private Float scoreCredito;

    @Column
    private Float saldo;

    public void desativar() {
        if (!correntista) {
            throw new RuntimeException("Esta conta já está desativada.");
        }
        correntista = false;
    }
}

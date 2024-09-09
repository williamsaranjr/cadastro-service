package com.williamsaran.cadastroservice.model;

import com.williamsaran.cadastroservice.exception.ContaDesativadaException;
import com.williamsaran.cadastroservice.model.dto.ClienteDTO;
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

    public void atualizarParcialmente(ClienteDTO dto) {
        if (dto.getNome() != null) {
            nome = dto.getNome();
        }
        if (dto.getTelefone() != null) {
            telefone = dto.getTelefone();
        }
        if (dto.getScoreCredito() != null) {
            scoreCredito = dto.getScoreCredito();
        }
        if (dto.getSaldo() != null) {
            saldo = dto.getSaldo();
        }
    }

    public void desativar() {
        if (!correntista) {
            throw new ContaDesativadaException();
        }
        correntista = false;
    }
}

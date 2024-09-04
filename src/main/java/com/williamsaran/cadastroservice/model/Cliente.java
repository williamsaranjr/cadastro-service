package com.williamsaran.cadastroservice.model;

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

    public ClienteDTO converter() {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(this.id);
        dto.setNome(this.nome);
        dto.setTelefone(this.telefone);
        dto.setCorrentista(this.correntista);
        dto.setScoreCredito(this.scoreCredito);
        dto.setSaldo(this.saldo);
        return dto;
    }
}

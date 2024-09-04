package com.williamsaran.cadastroservice.handler.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExceptionResponse {
    private final Date timestamp = new Date();
    private final String mensagem;

    public ExceptionResponse(String mensagem) {
        this.mensagem = mensagem;
    }
}

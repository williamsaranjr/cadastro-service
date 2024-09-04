package com.williamsaran.cadastroservice.handler;

import com.williamsaran.cadastroservice.exception.ClienteNaoEncontradoException;
import com.williamsaran.cadastroservice.exception.ContaDesativadaException;
import com.williamsaran.cadastroservice.handler.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ClienteNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleClienteNaoEncontradoException(ClienteNaoEncontradoException e) {
        return new ExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(ContaDesativadaException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleContaDesativada(ContaDesativadaException e) {
        return new ExceptionResponse(e.getMessage());
    }
}

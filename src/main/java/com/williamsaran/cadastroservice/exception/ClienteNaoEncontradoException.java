package com.williamsaran.cadastroservice.exception;

import java.util.logging.Logger;

public class ClienteNaoEncontradoException extends RuntimeException {
    public static final String MSG = "Cliente não encontrado";

    public ClienteNaoEncontradoException(Logger logger) {
        super(MSG);
        logger.severe(MSG);
    }

    public ClienteNaoEncontradoException() {
        super(MSG);
    }
}

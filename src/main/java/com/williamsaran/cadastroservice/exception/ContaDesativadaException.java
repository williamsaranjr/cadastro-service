package com.williamsaran.cadastroservice.exception;

public class ContaDesativadaException extends RuntimeException {
    private static final String MSG = "A conta já está desativada";

    public ContaDesativadaException() {
        super(MSG);
    }
}

package com.williamsaran.cadastroservice.exception;

public class ContaDesativadaException extends RuntimeException {
    private static final String MSG = "A conta já está desativada, portanto, não pode ser desativada novamente.";

    public ContaDesativadaException() {
        super(MSG);
    }
}

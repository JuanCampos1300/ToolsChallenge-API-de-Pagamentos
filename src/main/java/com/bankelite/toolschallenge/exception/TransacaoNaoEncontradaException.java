package com.bankelite.toolschallenge.exception;
public class TransacaoNaoEncontradaException extends RuntimeException {
    public TransacaoNaoEncontradaException(String id) { super("Transação não encontrada: " + id); }
}

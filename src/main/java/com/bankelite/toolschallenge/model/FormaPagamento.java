package com.bankelite.toolschallenge.model;

public class FormaPagamento {
    private String tipo;
    private int parcelas;

    public FormaPagamento() {}
    public FormaPagamento(String tipo, int parcelas) { this.tipo = tipo; this.parcelas = parcelas; }
    public String getTipo() { return tipo; }
    public int getParcelas() { return parcelas; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setParcelas(int parcelas) { this.parcelas = parcelas; }
}

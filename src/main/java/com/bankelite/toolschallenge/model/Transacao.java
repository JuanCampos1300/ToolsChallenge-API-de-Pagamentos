package com.bankelite.toolschallenge.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Transacao {
    private String id;
    private String cartaoMascarado;
    private BigDecimal valor;
    private String estabelecimento;
    private OffsetDateTime dataHora;
    private String nsu;
    private String codigoAutorizacao;
    private StatusTransacao status;
    private FormaPagamento formaPagamento;

    public Transacao() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCartaoMascarado() { return cartaoMascarado; }
    public void setCartaoMascarado(String cartaoMascarado) { this.cartaoMascarado = cartaoMascarado; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public String getEstabelecimento() { return estabelecimento; }
    public void setEstabelecimento(String estabelecimento) { this.estabelecimento = estabelecimento; }
    public OffsetDateTime getDataHora() { return dataHora; }
    public void setDataHora(OffsetDateTime dataHora) { this.dataHora = dataHora; }
    public String getNsu() { return nsu; }
    public void setNsu(String nsu) { this.nsu = nsu; }
    public String getCodigoAutorizacao() { return codigoAutorizacao; }
    public void setCodigoAutorizacao(String codigoAutorizacao) { this.codigoAutorizacao = codigoAutorizacao; }
    public StatusTransacao getStatus() { return status; }
    public void setStatus(StatusTransacao status) { this.status = status; }
    public FormaPagamento getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(FormaPagamento formaPagamento) { this.formaPagamento = formaPagamento; }
}

package com.bankelite.toolschallenge.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class PagamentoRequest {

    @Valid @NotNull
    private TransacaoReq transacao;

    public TransacaoReq getTransacao() { return transacao; }
    public void setTransacao(TransacaoReq transacao) { this.transacao = transacao; }

    // ----- objetos internos do JSON -----
    public static class TransacaoReq {
        @NotNull
        private String cartao; // aceita mascarado ou número
        private String id; // opcional no request
        @Valid @NotNull
        private DescricaoReq descricao;
        @Valid @NotNull
        private FormaPagamentoReq formaPagamento;

        public String getCartao() { return cartao; }
        public void setCartao(String cartao) { this.cartao = cartao; }
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public DescricaoReq getDescricao() { return descricao; }
        public void setDescricao(DescricaoReq descricao) { this.descricao = descricao; }
        public FormaPagamentoReq getFormaPagamento() { return formaPagamento; }
        public void setFormaPagamento(FormaPagamentoReq formaPagamento) { this.formaPagamento = formaPagamento; }
    }

    public static class DescricaoReq {
        @NotNull private String valor; // como string para bater com o enunciado
        @NotNull private String dataHora; // ignorado; servidor gera o próprio
        @NotNull private String estabelecimento;

        public String getValor() { return valor; }
        public void setValor(String valor) { this.valor = valor; }
        public String getDataHora() { return dataHora; }
        public void setDataHora(String dataHora) { this.dataHora = dataHora; }
        public String getEstabelecimento() { return estabelecimento; }
        public void setEstabelecimento(String estabelecimento) { this.estabelecimento = estabelecimento; }
    }

    public static class FormaPagamentoReq {
        @NotNull private String tipo;
        @NotNull private String parcelas; // string para bater com o enunciado

        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
        public String getParcelas() { return parcelas; }
        public void setParcelas(String parcelas) { this.parcelas = parcelas; }
    }
}

package com.bankelite.toolschallenge.dto.response;

public class PagamentoResponse {
    private TransacaoOut transacao;

    public PagamentoResponse() {}
    public PagamentoResponse(TransacaoOut transacao) { this.transacao = transacao; }

    public TransacaoOut getTransacao() { return transacao; }
    public void setTransacao(TransacaoOut transacao) { this.transacao = transacao; }

    public static class TransacaoOut {
        private String cartao;
        private String id;
        private DescricaoOut descricao;
        private FormaPagamentoOut formaPagamento;

        public TransacaoOut() {}
        public TransacaoOut(String cartao, String id, DescricaoOut descricao, FormaPagamentoOut formaPagamento) {
            this.cartao = cartao; this.id = id; this.descricao = descricao; this.formaPagamento = formaPagamento;
        }
        public String getCartao() { return cartao; }
        public void setCartao(String cartao) { this.cartao = cartao; }
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public DescricaoOut getDescricao() { return descricao; }
        public void setDescricao(DescricaoOut descricao) { this.descricao = descricao; }
        public FormaPagamentoOut getFormaPagamento() { return formaPagamento; }
        public void setFormaPagamento(FormaPagamentoOut formaPagamento) { this.formaPagamento = formaPagamento; }
    }

    public static class DescricaoOut {
        private String valor;
        private String dataHora;
        private String estabelecimento;
        private String nsu;
        private String codigoAutorizacao;
        private String status;

        public DescricaoOut() {}
        public DescricaoOut(String valor, String dataHora, String estabelecimento, String nsu, String codigoAutorizacao, String status) {
            this.valor = valor; this.dataHora = dataHora; this.estabelecimento = estabelecimento; this.nsu = nsu; this.codigoAutorizacao = codigoAutorizacao; this.status = status;
        }
        public String getValor() { return valor; }
        public void setValor(String valor) { this.valor = valor; }
        public String getDataHora() { return dataHora; }
        public void setDataHora(String dataHora) { this.dataHora = dataHora; }
        public String getEstabelecimento() { return estabelecimento; }
        public void setEstabelecimento(String estabelecimento) { this.estabelecimento = estabelecimento; }
        public String getNsu() { return nsu; }
        public void setNsu(String nsu) { this.nsu = nsu; }
        public String getCodigoAutorizacao() { return codigoAutorizacao; }
        public void setCodigoAutorizacao(String codigoAutorizacao) { this.codigoAutorizacao = codigoAutorizacao; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    public static class FormaPagamentoOut {
        private String tipo;
        private String parcelas;

        public FormaPagamentoOut() {}
        public FormaPagamentoOut(String tipo, String parcelas) { this.tipo = tipo; this.parcelas = parcelas; }
        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
        public String getParcelas() { return parcelas; }
        public void setParcelas(String parcelas) { this.parcelas = parcelas; }
    }
}

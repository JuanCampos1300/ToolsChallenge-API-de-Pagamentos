package com.bankelite.toolschallenge.mapper;

import com.bankelite.toolschallenge.dto.request.PagamentoRequest;
import com.bankelite.toolschallenge.dto.response.PagamentoResponse;
import com.bankelite.toolschallenge.model.FormaPagamento;
import com.bankelite.toolschallenge.model.StatusTransacao;
import com.bankelite.toolschallenge.model.Transacao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.UUID;

public class TransacaoMapper {

    private static final DateTimeFormatter BR_DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static Transacao toModel(PagamentoRequest req) {
        Transacao t = new Transacao();
        // usa o ID do request se vier; caso contrário, gera UUID
        String requestId = req.getTransacao().getId();
        t.setId(requestId != null && !requestId.isBlank() ? requestId : UUID.randomUUID().toString());
        String cartao = req.getTransacao().getCartao();
        t.setCartaoMascarado(maskPan(cartao));
        BigDecimal valor = new BigDecimal(req.getTransacao().getDescricao().getValor());
        t.setValor(valor);
        t.setEstabelecimento(req.getTransacao().getDescricao().getEstabelecimento());
        t.setDataHora(OffsetDateTime.now());
        t.setFormaPagamento(new FormaPagamento(
                req.getTransacao().getFormaPagamento().getTipo(),
                Integer.parseInt(req.getTransacao().getFormaPagamento().getParcelas())
        ));
        t.setNsu(randomDigits(10));
        t.setCodigoAutorizacao(randomDigits(9));
        return t;
    }

    public static PagamentoResponse toResponse(Transacao t) {
        String valorStr = t.getValor().setScale(2, RoundingMode.HALF_UP).toString();
        String dataHora = t.getDataHora().format(BR_DATE_TIME);
        PagamentoResponse.DescricaoOut desc = new PagamentoResponse.DescricaoOut(
                valorStr, dataHora, t.getEstabelecimento(), t.getNsu(), t.getCodigoAutorizacao(), t.getStatus().name()
        );
        PagamentoResponse.FormaPagamentoOut fp = new PagamentoResponse.FormaPagamentoOut(
                t.getFormaPagamento().getTipo(), String.valueOf(t.getFormaPagamento().getParcelas())
        );
        PagamentoResponse.TransacaoOut trx = new PagamentoResponse.TransacaoOut(
                t.getCartaoMascarado(), t.getId(), desc, fp
        );
        return new PagamentoResponse(trx);
    }

    private static String maskPan(String pan) {
        if (pan == null || pan.length() < 8) return "********";
        String ini = pan.substring(0, 4);
        String fim = pan.substring(pan.length() - 4);
        return ini + "*".repeat(pan.length() - 8) + fim;
    }

    private static String randomDigits(int len) {
        StringBuilder sb = new StringBuilder(len);
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < len; i++) sb.append(r.nextInt(10));
        return sb.toString();
    }
}

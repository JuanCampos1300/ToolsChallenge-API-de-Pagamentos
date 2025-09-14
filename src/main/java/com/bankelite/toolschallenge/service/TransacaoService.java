package com.bankelite.toolschallenge.service;

import com.bankelite.toolschallenge.dto.request.PagamentoRequest;
import com.bankelite.toolschallenge.exception.TransacaoNaoEncontradaException;
import com.bankelite.toolschallenge.mapper.TransacaoMapper;
import com.bankelite.toolschallenge.model.StatusTransacao;
import com.bankelite.toolschallenge.model.Transacao;
import com.bankelite.toolschallenge.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository repo;

    public TransacaoService(TransacaoRepository repo) { this.repo = repo; }

    public Transacao pagar(PagamentoRequest request) {
        Transacao t = TransacaoMapper.toModel(request);
        t.setStatus(StatusTransacao.AUTORIZADO);
        return repo.save(t);
    }

    public Transacao estornar(String id) {
        Transacao t = repo.findById(id).orElseThrow(() -> new TransacaoNaoEncontradaException(id));
        t.setStatus(StatusTransacao.NEGADO);
        return repo.save(t);
    }

    public Transacao consultarPorId(String id) {
        return repo.findById(id).orElseThrow(() -> new TransacaoNaoEncontradaException(id));
    }

    public List<Transacao> consultarTodos() {
        return repo.findAll();
    }
}

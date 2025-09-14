package com.bankelite.toolschallenge.repository;

import com.bankelite.toolschallenge.model.Transacao;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransacaoRepository {
    private final Map<String, Transacao> store = new ConcurrentHashMap<>();

    public Transacao save(Transacao t) { store.put(t.getId(), t); return t; }
    public Optional<Transacao> findById(String id) { return Optional.ofNullable(store.get(id)); }
    public List<Transacao> findAll() { return new ArrayList<>(store.values()); }
}

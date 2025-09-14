package com.bankelite.toolschallenge.controller;

import com.bankelite.toolschallenge.dto.request.PagamentoRequest;
import com.bankelite.toolschallenge.dto.response.PagamentoResponse;
import com.bankelite.toolschallenge.mapper.TransacaoMapper;
import com.bankelite.toolschallenge.model.Transacao;
import com.bankelite.toolschallenge.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) { this.service = service; }

    @PostMapping("/pagamento")
    public ResponseEntity<PagamentoResponse> pagamento(@Valid @RequestBody PagamentoRequest request) {
        Transacao t = service.pagar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(TransacaoMapper.toResponse(t));
    }

    @PostMapping("/{id}/estorno")
    public ResponseEntity<PagamentoResponse> estorno(@PathVariable String id) {
        Transacao t = service.estornar(id);
        return ResponseEntity.ok(TransacaoMapper.toResponse(t));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponse> consultaPorId(@PathVariable String id) {
        Transacao t = service.consultarPorId(id);
        return ResponseEntity.ok(TransacaoMapper.toResponse(t));
    }

    @GetMapping
    public ResponseEntity<List<PagamentoResponse>> consultaTodos() {
        List<PagamentoResponse> list = service.consultarTodos().stream()
                .map(TransacaoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}

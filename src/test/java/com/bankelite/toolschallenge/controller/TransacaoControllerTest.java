package com.bankelite.toolschallenge.controller;

import com.bankelite.toolschallenge.ToolsChallengeApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ToolsChallengeApplication.class)
@AutoConfigureMockMvc
class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void fluxoCompleto_pagamento_estorno_consulta() throws Exception {
        String body = "{\n" +
                "  \"transacao\": {\n" +
                "    \"cartao\": \"4444111122221234\",\n" +
                "    \"id\": \"100023568900001\",\n" +
                "    \"descricao\": {\n" +
                "      \"valor\": \"500.50\",\n" +
                "      \"dataHora\": \"01/05/2021 18:30:00\",\n" +
                "      \"estabelecimento\": \"PetShop Mundo cão\"\n" +
                "    },\n" +
                "    \"formaPagamento\": { \"tipo\": \"AVISTA\", \"parcelas\": \"1\" }\n" +
                "  }\n" +
                "}";

        MvcResult result = mockMvc.perform(post("/api/transacoes/pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transacao.id").exists())
                .andExpect(jsonPath("$.transacao.descricao.status").value("AUTORIZADO"))
                .andExpect(jsonPath("$.transacao.formaPagamento.tipo").value("AVISTA"))
                .andReturn();

        String json = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        String id = root.path("transacao").path("id").asText();

        assertThat(id).isNotBlank();


        mockMvc.perform(post("/api/transacoes/" + id + "/estorno"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transacao.id").value(id))
                .andExpect(jsonPath("$.transacao.descricao.status").value("NEGADO"));

        mockMvc.perform(get("/api/transacoes/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transacao.id").value(id));
    }
}

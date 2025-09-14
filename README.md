#  ToolsChallenge – API de Pagamentos

API REST em **Spring Boot 3 (Java 17)** que simula operações de **pagamento**, **consulta** e **estorno** de transações de cartão.  
O projeto aplica **POO, validação de dados, tratamento  de exceções e testes automatizados**.  
Documentação interativa disponível em **Swagger UI**.

---

## Funcionalidades
- **Pagamento** (`POST /api/transacoes/pagamento`) cria uma transação e retorna **201 Created**.
- **Consulta** (`GET /api/transacoes/{id}` | `GET /api/transacoes`) retorna a(s) transação(ões).
- **Estorno** (`POST /api/transacoes/{id}/estorno`) cancela uma transação existente.

---

## Tecnologias
- Java 17 • Spring Boot 3 • Spring Web • Bean Validation
- JUnit 5 + MockMvc
- Swagger (springdoc-openapi)
- Maven

---

##  Estrutura do Projeto
```
src/
 ├─ main/java/com/bankelite/toolschallenge
 │   ├─ controller/        # Endpoints REST
 │   ├─ dto/               # Requests & Responses (formato do desafio)
 │   ├─ exception/         # Handler global e erros
 │   ├─ mapper/            # Conversões Model <-> DTO
 │   ├─ model/             # Entidades de domínio
 │   ├─ repository/        # Persistência em memória
 │   └─ service/           # Regras de negócio
 └─ test/java/com/bankelite/toolschallenge
     └─ controller/        # Testes com MockMvc (fluxo completo)
```
Arquivo de build: **pom.xml**

---

##  Endpoints

### 1) Pagamento — `POST /api/transacoes/pagamento`
**Request**
```json
{
  "transacao": {
    "cartao": "4444111122221234",
    "id": "100023568900001",
    "descricao": {
      "valor": "500.50",
      "dataHora": "01/05/2021 18:30:00",
      "estabelecimento": "PetShop Mundo cão"
    },
    "formaPagamento": { "tipo": "AVISTA", "parcelas": "1" }
  }
}
```

**Response (201)**
```json
{
  "transacao": {
    "cartao": "4444********1234",
    "id": "100023568900001",
    "descricao": {
      "valor": "500.50",
      "dataHora": "01/05/2021 18:30:00",
      "estabelecimento": "PetShop Mundo cão",
      "nsu": "1234567890",
      "codigoAutorizacao": "147258369",
      "status": "AUTORIZADO"
    },
    "formaPagamento": { "tipo": "AVISTA", "parcelas": "1" }
  }
}
```

### 2) Consulta — `GET /api/transacoes/{id}`
**Response (200)**
```json
{
  "transacao": {
    "cartao": "4444********1234",
    "id": "100023568900001",
    "descricao": {
      "valor": "500.50",
      "dataHora": "01/05/2021 18:30:00",
      "estabelecimento": "PetShop Mundo cão",
      "nsu": "1234567890",
      "codigoAutorizacao": "147258369",
      "status": "AUTORIZADO"
    },
    "formaPagamento": { "tipo": "AVISTA", "parcelas": "1" }
  }
}
```

### 3) Estorno — `POST /api/transacoes/{id}/estorno`
**Response (200)**
```json
{
  "transacao": {
    "cartao": "4444********1234",
    "id": "100023568900001",
    "descricao": {
      "valor": "500.50",
      "dataHora": "01/05/2021 18:30:00",
      "estabelecimento": "PetShop Mundo cão",
      "nsu": "1234567890",
      "codigoAutorizacao": "147258369",
      "status": "CANCELADO"
    },
    "formaPagamento": { "tipo": "AVISTA", "parcelas": "1" }
  }
}
```

---

## Como Rodar
```bash
# testes
mvn clean test

# aplicação
mvn spring-boot:run
```
- App: `http://localhost:8080`  
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`

> Dica: se precisar limpar artefatos anteriores, rode `mvn clean` (a pasta `target/` é recreada automaticamente).

---

##  Testes Automatizados
Os testes cobrem o fluxo **pagamento → estorno → consulta** usando **MockMvc**.  
Para executar:
```bash
mvn test
```

---

##  Git – Como versionar este projeto

### Iniciar repositório
```bash
git init
git add .
git commit -m "feat: primeira versão da API de Pagamentos"
```

### Conectar a um repositório remoto (GitHub)
```bash
git branch -M main
git remote add origin https://github.com/<seu-usuario>/ToolsChallenge.git
git push -u origin main
```

### Fluxo sugerido de branches
- `main`: estável (releases)  
- `dev`: integração das features  
- `feature/<nome>`: novas funcionalidades

Exemplo:
```bash
git checkout -b feature/pagamento-negado
# ...edite os arquivos...
git add .
git commit -m "feat: regra de pagamento negado para valor > X"
git push -u origin feature/pagamento-negado
```

### .gitignore recomendado
Crie um arquivo `.gitignore` na raiz contendo:
```
/target/
/.idea/
*.iml
/.project
/.classpath
/.settings/
/.mvn/wrapper/maven-wrapper.jar
/.DS_Store
```
> A pasta `target/` é gerada pelo Maven e **não deve** ir para o Git.

---


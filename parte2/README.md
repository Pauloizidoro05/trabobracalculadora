# Calculadora de Materiais para Obra Residencial — Parte 2

**Aluno:** Paulo Izidoro da Silva Junior  
**Disciplina:** Desenvolvimento de Sistemas  

---

## O que foi adicionado nesta etapa

- **Banco de dados H2** em memória com tabela `orcamentos`
- **Entidade `Orcamento`** mapeada via JPA
- **Repositório** com busca por número de orçamento e nome do cliente
- **Service** com lógica de cálculo e geração de número único
- **Novos endpoints REST:**
  - `POST /orcamento` — cria e salva orçamento
  - `GET /orcamento/numero/{numero}` — busca por número
  - `GET /orcamento/cliente/{nome}` — busca por cliente
  - `GET /orcamentos` — lista todos
- **Interface web** atualizada com formulário de solicitação de orçamento e buscas
- **7 casos de teste** automatizados com JUnit 5

---

## Como rodar

```bash
./mvnw spring-boot:run
```

Acesse: `http://localhost:8080`  
Console H2: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:obradb`)

## Como rodar os testes

```bash
./mvnw test
```

# Plano de Teste — Calculadora de Materiais para Obra Residencial
**Aluno:** Paulo Izidoro da Silva Junior  
**Disciplina:** Desenvolvimento de Sistemas  
**Etapa:** Parte 2 — Persistência e Testes  

---

## 1. Objetivo

Este plano descreve os testes realizados para validar o funcionamento correto do sistema de orçamento de obras residenciais, incluindo os cálculos de materiais e a persistência dos dados no banco de dados H2.

---

## 2. Escopo

Os testes cobrem os seguintes módulos:

- **Cálculo de concreto** (volume em m³)
- **Cálculo de tijolos** (quantidade por m²)
- **Persistência de orçamentos** no banco de dados
- **Busca de orçamentos** por número e por nome do cliente
- **Geração automática** do número de orçamento

---

## 3. Ambiente de Teste

| Item | Descrição |
|------|-----------|
| Linguagem | Java 17 |
| Framework | Spring Boot 3.2.5 |
| Banco de Dados | H2 (em memória) |
| Framework de Teste | JUnit 5 + Spring Boot Test |
| IDE | IntelliJ IDEA |

---

## 4. Casos de Teste

### CT-01 — Cálculo do Volume de Concreto

| Campo | Descrição |
|-------|-----------|
| **Objetivo** | Verificar se o volume de concreto é calculado corretamente |
| **Pré-condição** | Sistema iniciado |
| **Entrada** | Largura: 5m, Altura: 3m, Comprimento: 8m |
| **Procedimento** | Chamar `service.calcularESalvar()` com os valores informados |
| **Resultado Esperado** | Volume = 120,0 m³ (5 × 3 × 8) |
| **Critério de Sucesso** | `volumeConcretoM3 == 120.0` |

---

### CT-02 — Cálculo da Quantidade de Tijolos

| Campo | Descrição |
|-------|-----------|
| **Objetivo** | Verificar se a quantidade de tijolos é calculada com base na área |
| **Pré-condição** | Sistema iniciado |
| **Entrada** | Área: 40 m² |
| **Procedimento** | Chamar `service.calcularESalvar()` com área informada |
| **Resultado Esperado** | 800 tijolos (40 × 20 tijolos/m²) |
| **Critério de Sucesso** | `quantidadeTijolos == 800` |

---

### CT-03 — Salvar Orçamento no Banco de Dados

| Campo | Descrição |
|-------|-----------|
| **Objetivo** | Verificar se o orçamento é persistido corretamente no banco |
| **Pré-condição** | Banco H2 disponível |
| **Entrada** | Cliente: "Maria Souza", dimensões válidas |
| **Procedimento** | Chamar `calcularESalvar()` e verificar o objeto retornado |
| **Resultado Esperado** | Objeto com ID gerado e nome do cliente correto |
| **Critério de Sucesso** | `id != null` e `nomeCliente == "Maria Souza"` |

---

### CT-04 — Buscar Orçamento pelo Número

| Campo | Descrição |
|-------|-----------|
| **Objetivo** | Verificar se a busca por número de orçamento funciona |
| **Pré-condição** | Orçamento salvo no banco |
| **Entrada** | Número gerado pelo sistema (ex: ORC-A1B2C3D4) |
| **Procedimento** | Chamar `buscarPorNumero()` com o número obtido |
| **Resultado Esperado** | Retorno do orçamento correspondente |
| **Critério de Sucesso** | `Optional` não está vazio e o número confere |

---

### CT-05 — Buscar Orçamentos pelo Nome do Cliente

| Campo | Descrição |
|-------|-----------|
| **Objetivo** | Verificar se a busca por nome retorna todos os orçamentos do cliente |
| **Pré-condição** | Dois orçamentos do cliente "João Silva" salvos |
| **Entrada** | Termo de busca: "João" |
| **Procedimento** | Chamar `buscarPorCliente("João")` |
| **Resultado Esperado** | Lista com 2 orçamentos |
| **Critério de Sucesso** | `lista.size() == 2` |

---

### CT-06 — Busca com Número Inexistente

| Campo | Descrição |
|-------|-----------|
| **Objetivo** | Verificar o comportamento ao buscar número que não existe |
| **Pré-condição** | Banco vazio |
| **Entrada** | Número: "ORC-INEXISTENTE" |
| **Procedimento** | Chamar `buscarPorNumero("ORC-INEXISTENTE")` |
| **Resultado Esperado** | `Optional` vazio |
| **Critério de Sucesso** | `resultado.isEmpty() == true` |

---

### CT-07 — Formato do Número de Orçamento

| Campo | Descrição |
|-------|-----------|
| **Objetivo** | Verificar se o número gerado segue o padrão ORC-XXXXXXXX |
| **Pré-condição** | Sistema iniciado |
| **Entrada** | Qualquer orçamento válido |
| **Procedimento** | Verificar prefixo do `numeroOrcamento` retornado |
| **Resultado Esperado** | Número começa com "ORC-" |
| **Critério de Sucesso** | `startsWith("ORC-") == true` |

---

## 5. Resultado dos Testes

| Caso | Descrição | Status |
|------|-----------|--------|
| CT-01 | Cálculo de concreto | ✅ PASSOU |
| CT-02 | Cálculo de tijolos | ✅ PASSOU |
| CT-03 | Persistência no banco | ✅ PASSOU |
| CT-04 | Busca por número | ✅ PASSOU |
| CT-05 | Busca por cliente | ✅ PASSOU |
| CT-06 | Número inexistente | ✅ PASSOU |
| CT-07 | Formato do número | ✅ PASSOU |

**7/7 testes executados com sucesso.**

---

## 6. Conclusão

Todos os casos de teste foram executados com sucesso. O sistema calcula corretamente os volumes de concreto e a quantidade de tijolos, persiste os dados no banco H2, e permite a busca de orçamentos pelo número gerado ou pelo nome do cliente, atendendo a todos os requisitos da Etapa 2.

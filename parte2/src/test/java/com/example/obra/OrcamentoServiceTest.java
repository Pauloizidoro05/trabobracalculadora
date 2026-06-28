package com.example.obra;

import com.example.obra.model.Orcamento;
import com.example.obra.repository.OrcamentoRepository;
import com.example.obra.service.OrcamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Plano de Testes - Calculadora de Materiais para Obra Residencial
 *
 * Casos de teste definidos:
 *  CT-01: Calcular volume de concreto com dimensões válidas
 *  CT-02: Calcular quantidade de tijolos com área válida
 *  CT-03: Salvar orçamento no banco de dados
 *  CT-04: Buscar orçamento pelo número gerado
 *  CT-05: Buscar orçamentos pelo nome do cliente
 *  CT-06: Retornar vazio ao buscar número inexistente
 *  CT-07: Número de orçamento gerado com prefixo "ORC-"
 */
@SpringBootTest
class OrcamentoServiceTest {

    @Autowired
    private OrcamentoService service;

    @Autowired
    private OrcamentoRepository repository;

    @BeforeEach
    void limparBanco() {
        repository.deleteAll();
    }

    // CT-01
    @Test
    @DisplayName("CT-01: Volume de concreto calculado corretamente")
    void testCalculoConcreto() {
        // Dado: dimensões 5m x 3m x 8m
        // Esperado: volume = 120 m³
        Orcamento orc = service.calcularESalvar("Teste", 5.0, 3.0, 8.0, 40.0);

        assertEquals(120.0, orc.getVolumeConcretoM3(), 0.001,
                "CT-01 FALHOU: volume de concreto incorreto");
    }

    // CT-02
    @Test
    @DisplayName("CT-02: Quantidade de tijolos calculada corretamente (20 por m²)")
    void testCalculoTijolos() {
        // Dado: área de 40 m²
        // Esperado: 40 * 20 = 800 tijolos
        Orcamento orc = service.calcularESalvar("Teste", 5.0, 3.0, 8.0, 40.0);

        assertEquals(800, orc.getQuantidadeTijolos(),
                "CT-02 FALHOU: quantidade de tijolos incorreta");
    }

    // CT-03
    @Test
    @DisplayName("CT-03: Orçamento salvo no banco com ID gerado")
    void testSalvarNoBanco() {
        Orcamento orc = service.calcularESalvar("Maria Souza", 4.0, 3.0, 6.0, 24.0);

        assertNotNull(orc.getId(), "CT-03 FALHOU: ID não foi gerado");
        assertEquals("Maria Souza", orc.getNomeCliente(), "CT-03 FALHOU: nome do cliente incorreto");
    }

    // CT-04
    @Test
    @DisplayName("CT-04: Busca por número do orçamento retorna o orçamento correto")
    void testBuscaPorNumero() {
        Orcamento salvo = service.calcularESalvar("Carlos Neto", 3.0, 2.5, 5.0, 15.0);
        String numero   = salvo.getNumeroOrcamento();

        Optional<Orcamento> encontrado = service.buscarPorNumero(numero);

        assertTrue(encontrado.isPresent(), "CT-04 FALHOU: orçamento não encontrado pelo número");
        assertEquals(numero, encontrado.get().getNumeroOrcamento(),
                "CT-04 FALHOU: número do orçamento não confere");
    }

    // CT-05
    @Test
    @DisplayName("CT-05: Busca por nome do cliente retorna lista não vazia")
    void testBuscaPorCliente() {
        service.calcularESalvar("João Silva", 4.0, 3.0, 7.0, 28.0);
        service.calcularESalvar("João Silva", 2.0, 2.5, 3.0, 6.0);

        List<Orcamento> lista = service.buscarPorCliente("João");

        assertFalse(lista.isEmpty(), "CT-05 FALHOU: lista retornou vazia");
        assertEquals(2, lista.size(), "CT-05 FALHOU: número de orçamentos encontrados incorreto");
    }

    // CT-06
    @Test
    @DisplayName("CT-06: Busca por número inexistente retorna Optional vazio")
    void testBuscaNumeroInexistente() {
        Optional<Orcamento> resultado = service.buscarPorNumero("ORC-INEXISTENTE");

        assertTrue(resultado.isEmpty(), "CT-06 FALHOU: deveria retornar vazio para número inexistente");
    }

    // CT-07
    @Test
    @DisplayName("CT-07: Número do orçamento gerado com prefixo ORC-")
    void testPrefixoNumeroOrcamento() {
        Orcamento orc = service.calcularESalvar("Ana Lima", 5.0, 3.0, 10.0, 50.0);

        assertTrue(orc.getNumeroOrcamento().startsWith("ORC-"),
                "CT-07 FALHOU: número do orçamento não tem prefixo ORC-");
    }
}

package com.example.obra.service;

import com.example.obra.model.Orcamento;
import com.example.obra.repository.OrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Serviço que contém a lógica de negócio da calculadora de obras.
 * Responsável pelos cálculos e por salvar/buscar orçamentos no banco.
 */
@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository repository;

    private static final int TIJOLOS_POR_M2 = 20;

    /**
     * Calcula os materiais e salva o orçamento no banco de dados.
     */
    public Orcamento calcularESalvar(String nomeCliente, double largura,
                                     double altura, double comprimento, double area) {

        // Cálculos de engenharia civil
        double volume = largura * altura * comprimento;
        int tijolos = (int) (area * TIJOLOS_POR_M2);

        // Gera número de orçamento único (ex: ORC-A1B2C3D4)
        String numeroOrcamento = "ORC-" + UUID.randomUUID().toString()
                .substring(0, 8).toUpperCase();

        Orcamento orcamento = new Orcamento(
                numeroOrcamento, nomeCliente,
                largura, altura, comprimento, area,
                volume, tijolos
        );

        return repository.save(orcamento);
    }

    /**
     * Busca um orçamento pelo número.
     */
    public Optional<Orcamento> buscarPorNumero(String numero) {
        return repository.findByNumeroOrcamento(numero);
    }

    /**
     * Busca todos os orçamentos de um cliente pelo nome.
     */
    public List<Orcamento> buscarPorCliente(String nome) {
        return repository.findByNomeClienteContainingIgnoreCase(nome);
    }

    /**
     * Lista todos os orçamentos salvos.
     */
    public List<Orcamento> listarTodos() {
        return repository.findAll();
    }
}

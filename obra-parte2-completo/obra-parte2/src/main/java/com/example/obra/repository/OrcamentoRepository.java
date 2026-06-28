package com.example.obra.repository;

import com.example.obra.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório responsável pelo acesso ao banco de dados da tabela de orçamentos.
 * Permite buscar orçamentos pelo número ou pelo nome do cliente.
 */
@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

    // Busca por número do orçamento (identificador único)
    Optional<Orcamento> findByNumeroOrcamento(String numeroOrcamento);

    // Busca por nome do cliente (pode retornar vários orçamentos)
    List<Orcamento> findByNomeClienteContainingIgnoreCase(String nomeCliente);
}

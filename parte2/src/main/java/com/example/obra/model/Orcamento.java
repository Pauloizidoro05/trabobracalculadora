package com.example.obra.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidade que representa um orçamento de obra no banco de dados.
 * Cada orçamento é identificado por um número único e pelo nome do cliente.
 */
@Entity
@Table(name = "orcamentos")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Número do orçamento gerado automaticamente para busca posterior
    @Column(name = "numero_orcamento", unique = true, nullable = false)
    private String numeroOrcamento;

    // Dados do cliente
    @Column(name = "nome_cliente", nullable = false)
    private String nomeCliente;

    // Dados da planta - dimensões do cômodo/área
    @Column(name = "largura_m")
    private Double larguraM;

    @Column(name = "altura_m")
    private Double alturaM;

    @Column(name = "comprimento_m")
    private Double comprimentoM;

    @Column(name = "area_m2")
    private Double areaM2;

    // Resultados calculados
    @Column(name = "volume_concreto_m3")
    private Double volumeConcretoM3;

    @Column(name = "quantidade_tijolos")
    private Integer quantidadeTijolos;

    // Data/hora do orçamento
    @Column(name = "data_orcamento")
    private LocalDateTime dataOrcamento;

    // Construtores
    public Orcamento() {
    }

    public Orcamento(String numeroOrcamento, String nomeCliente, Double larguraM,
                     Double alturaM, Double comprimentoM, Double areaM2,
                     Double volumeConcretoM3, Integer quantidadeTijolos) {
        this.numeroOrcamento = numeroOrcamento;
        this.nomeCliente = nomeCliente;
        this.larguraM = larguraM;
        this.alturaM = alturaM;
        this.comprimentoM = comprimentoM;
        this.areaM2 = areaM2;
        this.volumeConcretoM3 = volumeConcretoM3;
        this.quantidadeTijolos = quantidadeTijolos;
        this.dataOrcamento = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroOrcamento() { return numeroOrcamento; }
    public void setNumeroOrcamento(String numeroOrcamento) { this.numeroOrcamento = numeroOrcamento; }

    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

    public Double getLarguraM() { return larguraM; }
    public void setLarguraM(Double larguraM) { this.larguraM = larguraM; }

    public Double getAlturaM() { return alturaM; }
    public void setAlturaM(Double alturaM) { this.alturaM = alturaM; }

    public Double getComprimentoM() { return comprimentoM; }
    public void setComprimentoM(Double comprimentoM) { this.comprimentoM = comprimentoM; }

    public Double getAreaM2() { return areaM2; }
    public void setAreaM2(Double areaM2) { this.areaM2 = areaM2; }

    public Double getVolumeConcretoM3() { return volumeConcretoM3; }
    public void setVolumeConcretoM3(Double volumeConcretoM3) { this.volumeConcretoM3 = volumeConcretoM3; }

    public Integer getQuantidadeTijolos() { return quantidadeTijolos; }
    public void setQuantidadeTijolos(Integer quantidadeTijolos) { this.quantidadeTijolos = quantidadeTijolos; }

    public LocalDateTime getDataOrcamento() { return dataOrcamento; }
    public void setDataOrcamento(LocalDateTime dataOrcamento) { this.dataOrcamento = dataOrcamento; }
}

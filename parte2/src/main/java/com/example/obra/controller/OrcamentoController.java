package com.example.obra.controller;

import com.example.obra.model.Orcamento;
import com.example.obra.service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller REST que expõe os endpoints da calculadora de obras.
 * Mantém compatibilidade com a versão anterior e adiciona novos endpoints
 * para orçamentos com persistência no banco de dados.
 */
@RestController
@CrossOrigin(origins = "*")
public class OrcamentoController {

    @Autowired
    private OrcamentoService service;

    // ---- Endpoints da versão anterior (mantidos) ----

    @GetMapping("/teste")
    public String teste() {
        return "API funcionando 🚀";
    }

    @GetMapping("/concreto")
    public Map<String, Object> calcularConcreto(double largura, double altura, double comprimento) {
        double volume = largura * altura * comprimento;
        return Map.of(
                "largura", largura,
                "altura", altura,
                "comprimento", comprimento,
                "volume_m3", volume
        );
    }

    @GetMapping("/tijolos")
    public Map<String, Object> calcularTijolos(double area) {
        int tijolosPorM2 = 20;
        int total = (int) (area * tijolosPorM2);
        return Map.of(
                "area_m2", area,
                "tijolos_por_m2", tijolosPorM2,
                "total_tijolos", total
        );
    }

    // ---- Novos endpoints com banco de dados ----

    /**
     * POST /orcamento
     * Recebe os dados da planta e salva o orçamento no banco.
     * Retorna o orçamento completo com número gerado automaticamente.
     */
    @PostMapping("/orcamento")
    public ResponseEntity<Orcamento> criarOrcamento(@RequestBody Map<String, Object> body) {
        String nomeCliente = (String) body.get("nomeCliente");
        double largura     = Double.parseDouble(body.get("largura").toString());
        double altura      = Double.parseDouble(body.get("altura").toString());
        double comprimento = Double.parseDouble(body.get("comprimento").toString());
        double area        = Double.parseDouble(body.get("area").toString());

        Orcamento salvo = service.calcularESalvar(nomeCliente, largura, altura, comprimento, area);
        return ResponseEntity.ok(salvo);
    }

    /**
     * GET /orcamento/numero/{numero}
     * Busca um orçamento pelo número (ex: ORC-A1B2C3D4).
     */
    @GetMapping("/orcamento/numero/{numero}")
    public ResponseEntity<?> buscarPorNumero(@PathVariable String numero) {
        Optional<Orcamento> resultado = service.buscarPorNumero(numero);
        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        }
        return ResponseEntity.status(404).body(Map.of("erro", "Orçamento não encontrado"));
    }

    /**
     * GET /orcamento/cliente/{nome}
     * Busca todos os orçamentos de um cliente pelo nome.
     */
    @GetMapping("/orcamento/cliente/{nome}")
    public ResponseEntity<?> buscarPorCliente(@PathVariable String nome) {
        List<Orcamento> lista = service.buscarPorCliente(nome);
        if (lista.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("erro", "Nenhum orçamento encontrado para esse cliente"));
        }
        return ResponseEntity.ok(lista);
    }

    /**
     * GET /orcamentos
     * Lista todos os orçamentos salvos.
     */
    @GetMapping("/orcamentos")
    public List<Orcamento> listarTodos() {
        return service.listarTodos();
    }
}

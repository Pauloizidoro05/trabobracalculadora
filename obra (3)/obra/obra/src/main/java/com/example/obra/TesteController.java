package com.example.obra;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class TesteController {

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
}
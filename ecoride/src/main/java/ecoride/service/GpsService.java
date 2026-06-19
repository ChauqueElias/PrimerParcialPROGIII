package ecoride.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class GpsService {

    public List<String> deduplicarAlertas(List<String> alertas) {
        HashSet<String> vistas = new HashSet<>();
        List<String> resultado = new ArrayList<>();

        for (int i = 0; i < alertas.size(); i++) {
            String alerta = alertas.get(i);
            if (vistas.add(alerta)) {
                resultado.add(alerta);
            }
        }

        return resultado;
    }
}

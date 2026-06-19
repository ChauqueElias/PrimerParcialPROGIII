package ecoride.controller;

import ecoride.dto.VehiculoDTO;
import ecoride.service.AlquilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final AlquilerService alquilerService;

    @Autowired
    public VehiculoController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping("/prioridad-carga")
    public List<VehiculoDTO> getPrioridadCarga() {
        return alquilerService.getFlotaOrdenadaPorBateria();
    }

    @GetMapping("/tarifa-descendente")
    public List<VehiculoDTO> getTarifaDescendente() {
        return alquilerService.getFlotaOrdenadaPorTarifa();
    }
}

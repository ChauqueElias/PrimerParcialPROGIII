package ecoride.controller;

import ecoride.dto.AlquilerResponseDTO;
import ecoride.service.AlquilerService;
import ecoride.service.GpsService;
import ecoride.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;
    private final GpsService gpsService;

    @Autowired
    public AlquilerController(AlquilerService alquilerService, GpsService gpsService) {
        this.alquilerService = alquilerService;
        this.gpsService = gpsService;
    }

    @GetMapping("/desbloquear")
    public AlquilerResponseDTO desbloquear(
            @RequestParam Long idUsuario,
            @RequestParam String patente,
            @RequestParam(defaultValue = "30") int minutos) {
        return alquilerService.desbloquearVehiculo(idUsuario, patente, minutos);
    }

    @PutMapping("/finalizar")
    public AlquilerResponseDTO finalizar(@RequestParam String patente) {
        return alquilerService.finalizarViaje(patente);
    }

    @PutMapping("/reparacion")
    public AlquilerResponseDTO enviarReparacion(@RequestParam String patente) {
        return alquilerService.enviarAReparacion(patente);
    }

    @PutMapping("/restaurar")
    public AlquilerResponseDTO restaurar(@RequestParam String patente) {
        return alquilerService.restaurarVehiculo(patente);
    }

    @PostMapping("/criterio")
    public String cambiarCriterio(@RequestParam String tipo) {
        if (tipo.equalsIgnoreCase("HORA_PICO")) {
            alquilerService.setCriterioTarifa(new CriterioHoraPico());
            return "Criterio cambiado a Hora Pico (+40%)";
        } else if (tipo.equalsIgnoreCase("CLIMATICO")) {
            alquilerService.setCriterioTarifa(new CriterioTemporalClimatico());
            return "Criterio cambiado a Temporal Climatico (+$150)";
        } else {
            alquilerService.setCriterioTarifa(new CriterioEstandar());
            return "Criterio cambiado a Estandar";
        }
    }

    @PostMapping("/gps/deduplicar")
    public List<String> deduplicarAlertas(@RequestBody List<String> alertas) {
        return gpsService.deduplicarAlertas(alertas);
    }
}

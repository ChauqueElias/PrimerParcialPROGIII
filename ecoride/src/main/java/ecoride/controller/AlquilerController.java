package ecoride.controller;

import ecoride.service.AlquilerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlquilerController {

    private AlquilerService alquilerService = new AlquilerService();

    @GetMapping("/api/alquileres/desbloquear")
    public String desbloquear(
            @RequestParam Long idUsuario,
            @RequestParam String patente
    ) {

        return alquilerService.desbloquearVehiculo(idUsuario, patente);
    }
}
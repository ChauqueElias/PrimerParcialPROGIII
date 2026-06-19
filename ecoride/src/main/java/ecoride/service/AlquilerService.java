package ecoride.service;

import ecoride.dto.AlquilerResponseDTO;
import ecoride.dto.VehiculoDTO;
import ecoride.model.*;
import ecoride.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class AlquilerService {

    private final PagoService pagoService;

    private HashMap<String, Vehiculo> vehiculos = new HashMap<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private CriterioTarifa criterioTarifa = new CriterioEstandar();

    @Autowired
    public AlquilerService(PagoService pagoService) {
        this.pagoService = pagoService;

        Vehiculo m = new Monopatin("ABC123", 80, 1000, true);
        Vehiculo b = new BicicletaElectrica("XYZ789", 50, 1200, 30);
        vehiculos.put(m.getPatente().toUpperCase(), m);
        vehiculos.put(b.getPatente().toUpperCase(), b);

        usuarios.add(new UsuarioRegular(1L, "Juan"));
        usuarios.add(new UsuarioPremium(2L, "Maria"));
    }

    public void setCriterioTarifa(CriterioTarifa criterio) {
        this.criterioTarifa = criterio;
    }

    public Vehiculo buscarVehiculo(String patente) {
        return vehiculos.get(patente.toUpperCase());
    }

    public Usuario buscarUsuario(Long id) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                return usuarios.get(i);
            }
        }
        return null;
    }

    public AlquilerResponseDTO desbloquearVehiculo(Long idUsuario, String patente, int minutos) {
        Vehiculo vehiculo = buscarVehiculo(patente);

        if (vehiculo == null) {
            return new AlquilerResponseDTO("Vehículo no encontrado", patente, 0, 0, "DESCONOCIDO");
        }

        if (vehiculo.getBateria() < 15) {
            return new AlquilerResponseDTO("Batería insuficiente", patente, 0, 0, vehiculo.getEstadoActual());
        }

        try {
            vehiculo.iniciarViaje();
        } catch (IllegalStateException e) {
            return new AlquilerResponseDTO(e.getMessage(), patente, 0, 0, vehiculo.getEstadoActual());
        }

        Usuario usuario = buscarUsuario(idUsuario);
        if (usuario == null) {
            vehiculo.finalizarViaje();
            return new AlquilerResponseDTO("Usuario no encontrado", patente, 0, 0, vehiculo.getEstadoActual());
        }

        double tarifa = criterioTarifa.calcular(vehiculo.getTarifaBase(), minutos);
        double total = usuario.aplicarDescuento(tarifa);
        pagoService.procesarPago(total);

        return new AlquilerResponseDTO("Viaje iniciado correctamente", patente, minutos, total, vehiculo.getEstadoActual());
    }

    public AlquilerResponseDTO finalizarViaje(String patente) {
        Vehiculo vehiculo = buscarVehiculo(patente);
        if (vehiculo == null) {
            return new AlquilerResponseDTO("Vehículo no encontrado", patente, 0, 0, "DESCONOCIDO");
        }
        try {
            vehiculo.finalizarViaje();
            return new AlquilerResponseDTO("Viaje finalizado", patente, 0, 0, vehiculo.getEstadoActual());
        } catch (IllegalStateException e) {
            return new AlquilerResponseDTO(e.getMessage(), patente, 0, 0, vehiculo.getEstadoActual());
        }
    }

    public AlquilerResponseDTO enviarAReparacion(String patente) {
        Vehiculo vehiculo = buscarVehiculo(patente);
        if (vehiculo == null) {
            return new AlquilerResponseDTO("Vehículo no encontrado", patente, 0, 0, "DESCONOCIDO");
        }
        try {
            vehiculo.enviarAReparacion();
            return new AlquilerResponseDTO("Vehículo enviado a reparación", patente, 0, 0, vehiculo.getEstadoActual());
        } catch (IllegalStateException e) {
            return new AlquilerResponseDTO(e.getMessage(), patente, 0, 0, vehiculo.getEstadoActual());
        }
    }

    public AlquilerResponseDTO restaurarVehiculo(String patente) {
        Vehiculo vehiculo = buscarVehiculo(patente);
        if (vehiculo == null) {
            return new AlquilerResponseDTO("Vehículo no encontrado", patente, 0, 0, "DESCONOCIDO");
        }
        try {
            vehiculo.volverAEspera();
            return new AlquilerResponseDTO("Vehículo restaurado a espera", patente, 0, 0, vehiculo.getEstadoActual());
        } catch (IllegalStateException e) {
            return new AlquilerResponseDTO(e.getMessage(), patente, 0, 0, vehiculo.getEstadoActual());
        }
    }

    public List<VehiculoDTO> getFlotaOrdenadaPorBateria() {
        List<Vehiculo> lista = new ArrayList<>(vehiculos.values());
        Collections.sort(lista);
        return convertirADTO(lista);
    }

    public List<VehiculoDTO> getFlotaOrdenadaPorTarifa() {
        List<Vehiculo> lista = new ArrayList<>(vehiculos.values());
        Collections.sort(lista, new ComparadorPorTarifa());
        return convertirADTO(lista);
    }

    private List<VehiculoDTO> convertirADTO(List<Vehiculo> lista) {
        List<VehiculoDTO> resultado = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            Vehiculo v = lista.get(i);
            resultado.add(new VehiculoDTO(v.getPatente(), v.getBateria(), v.getTarifaBase(), v.getEstadoActual(), v.getTipo()));
        }
        return resultado;
    }
}

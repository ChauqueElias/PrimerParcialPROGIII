package ecoride.service;

import ecoride.model.*;

import java.util.ArrayList;
import java.util.List;

public class AlquilerService {

    private List<Vehiculo> vehiculos = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    private PagoService pagoService = new PagoService();

    public AlquilerService() {

        vehiculos.add(new Monopatin("ABC123", 80, 1000, true));
        vehiculos.add(new BicicletaElectrica("XYZ789", 50, 1200, 30));

        usuarios.add(new UsuarioRegular(1L, "Juan"));
        usuarios.add(new UsuarioPremium(2L, "Maria"));
    }

    public Vehiculo buscarVehiculo(String patente) {

        for (Vehiculo v : vehiculos) {
            if (v.getPatente().equalsIgnoreCase(patente)) {
                return v;
            }
        }

        return null;
    }

    public Usuario buscarUsuario(Long id) {

        for (Usuario u : usuarios) {
            if (u.getId().equals(id)) {
                return u;
            }
        }

        return null;
    }

    public String desbloquearVehiculo(Long idUsuario, String patente) {

        Vehiculo vehiculo = buscarVehiculo(patente);

        if (vehiculo == null) {
            return "Vehículo no encontrado";
        }

        if (vehiculo.getBateria() < 15) {
            return "Batería insuficiente";
        }

        Usuario usuario = buscarUsuario(idUsuario);

        double tarifa = vehiculo.calcularTarifa();

        double total = usuario.aplicarDescuento(tarifa);

    String pago = pagoService.procesarPago("TARJETA", total);

        return "Vehículo desbloqueado. " + pago;
    }
}
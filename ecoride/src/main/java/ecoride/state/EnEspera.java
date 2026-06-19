package ecoride.state;

import ecoride.model.Vehiculo;

public class EnEspera implements EstadoVehiculo {

    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        vehiculo.setEstado(new EnViaje());
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehículo no está en viaje.");
    }

    @Override
    public void enviarAReparacion(Vehiculo vehiculo) {
        vehiculo.setEstado(new EnReparacion());
    }

    @Override
    public void volverAEspera(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehículo ya está en espera.");
    }

    @Override
    public String getNombre() {
        return "EN_ESPERA";
    }
}

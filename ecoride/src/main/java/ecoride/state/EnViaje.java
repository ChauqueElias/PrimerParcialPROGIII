package ecoride.state;

import ecoride.model.Vehiculo;

public class EnViaje implements EstadoVehiculo {

    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehículo ya está en viaje.");
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        vehiculo.setEstado(new EnEspera());
    }

    @Override
    public void enviarAReparacion(Vehiculo vehiculo) {
        throw new IllegalStateException("No se puede enviar a reparación un vehículo en viaje.");
    }

    @Override
    public void volverAEspera(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehículo está en viaje, debe finalizarlo primero.");
    }

    @Override
    public String getNombre() {
        return "EN_VIAJE";
    }
}

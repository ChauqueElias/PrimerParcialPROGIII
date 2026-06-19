package ecoride.state;

import ecoride.model.Vehiculo;

public class EnReparacion implements EstadoVehiculo {

    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehículo está en reparación, no puede iniciar viaje.");
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehículo no está en viaje.");
    }

    @Override
    public void enviarAReparacion(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehículo ya está en reparación.");
    }

    @Override
    public void volverAEspera(Vehiculo vehiculo) {
        vehiculo.setEstado(new EnEspera());
    }

    @Override
    public String getNombre() {
        return "EN_REPARACION";
    }
}

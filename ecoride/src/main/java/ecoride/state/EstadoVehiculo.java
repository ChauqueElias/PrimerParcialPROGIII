package ecoride.state;

import ecoride.model.Vehiculo;

public interface EstadoVehiculo {
    void iniciarViaje(Vehiculo vehiculo);
    void finalizarViaje(Vehiculo vehiculo);
    void enviarAReparacion(Vehiculo vehiculo);
    void volverAEspera(Vehiculo vehiculo);
    String getNombre();
}

package ecoride.model;

import ecoride.state.EnEspera;
import ecoride.state.EstadoVehiculo;

public abstract class Vehiculo implements Comparable<Vehiculo> {

    protected String patente;
    protected int bateria;
    protected double tarifaBase;
    private EstadoVehiculo estado;

    public Vehiculo(String patente, int bateria, double tarifaBase) {
        this.patente = patente;
        this.bateria = bateria;
        this.tarifaBase = tarifaBase;
        this.estado = new EnEspera();
    }

    public String getPatente() { return patente; }
    public int getBateria() { return bateria; }
    public double getTarifaBase() { return tarifaBase; }
    public String getEstadoActual() { return estado.getNombre(); }

    public void setEstado(EstadoVehiculo estado) {
        this.estado = estado;
    }

    public void iniciarViaje() {
        estado.iniciarViaje(this);
    }

    public void finalizarViaje() {
        estado.finalizarViaje(this);
    }

    public void enviarAReparacion() {
        estado.enviarAReparacion(this);
    }

    public void volverAEspera() {
        estado.volverAEspera(this);
    }

    public abstract double calcularTarifa();

    public abstract String getTipo();

    @Override
    public int compareTo(Vehiculo otro) {
        return Integer.compare(this.bateria, otro.bateria);
    }
}

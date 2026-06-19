package ecoride.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstacionAnclaje {

    private String nombre;
    private List<Vehiculo> vehiculos;

    public EstacionAnclaje(String nombre) {
        this.nombre = nombre;
        this.vehiculos = new ArrayList<>();
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    public Vehiculo buscarVehiculo(String patente) {
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getPatente().equalsIgnoreCase(patente)) {
                return vehiculos.get(i);
            }
        }
        return null;
    }

    public List<Vehiculo> ordenarPorPrioridadCarga() {
        List<Vehiculo> copia = new ArrayList<>(vehiculos);
        Collections.sort(copia);
        return copia;
    }

    public List<Vehiculo> ordenarPorTarifa() {
        List<Vehiculo> copia = new ArrayList<>(vehiculos);
        Collections.sort(copia, new ComparadorPorTarifa());
        return copia;
    }

    public String getNombre() { return nombre; }
    public List<Vehiculo> getVehiculos() { return vehiculos; }
}

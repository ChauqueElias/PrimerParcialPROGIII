package ecoride.model;

public class BicicletaElectrica extends Vehiculo {

    private int capacidadCanasto;

    public BicicletaElectrica(String patente, int bateria, double tarifaBase, int capacidadCanasto) {
        super(patente, bateria, tarifaBase);
        this.capacidadCanasto = capacidadCanasto;
    }

    public int getCapacidadCanasto() { return capacidadCanasto; }

    @Override
    public double calcularTarifa() {
        return tarifaBase + 300;
    }

    @Override
    public String getTipo() {
        return "BicicletaElectrica";
    }
}

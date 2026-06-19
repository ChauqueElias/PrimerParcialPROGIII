package ecoride.model;

public class Monopatin extends Vehiculo {

    private boolean amortiguacion;

    public Monopatin(String patente, int bateria, double tarifaBase, boolean amortiguacion) {
        super(patente, bateria, tarifaBase);
        this.amortiguacion = amortiguacion;
    }

    public boolean isAmortiguacion() { return amortiguacion; }

    @Override
    public double calcularTarifa() {
        return tarifaBase + 500;
    }

    @Override
    public String getTipo() {
        return "Monopatin";
    }
}

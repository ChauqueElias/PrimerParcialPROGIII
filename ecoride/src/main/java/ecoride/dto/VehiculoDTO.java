package ecoride.dto;

public class VehiculoDTO {

    private String patente;
    private int bateria;
    private double tarifaBase;
    private String estadoActual;
    private String tipo;

    public VehiculoDTO(String patente, int bateria, double tarifaBase, String estadoActual, String tipo) {
        this.patente = patente;
        this.bateria = bateria;
        this.tarifaBase = tarifaBase;
        this.estadoActual = estadoActual;
        this.tipo = tipo;
    }

    public String getPatente() { return patente; }
    public int getBateria() { return bateria; }
    public double getTarifaBase() { return tarifaBase; }
    public String getEstadoActual() { return estadoActual; }
    public String getTipo() { return tipo; }
}

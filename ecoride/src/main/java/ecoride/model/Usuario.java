package ecoride.model;

public abstract class Usuario {

    protected Long id;
    protected String nombre;

    public Usuario(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract double aplicarDescuento(double monto);
}
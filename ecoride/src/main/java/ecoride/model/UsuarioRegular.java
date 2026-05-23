package ecoride.model;

public class UsuarioRegular extends Usuario {

    public UsuarioRegular(Long id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double aplicarDescuento(double monto) {
        return monto;
    }
}
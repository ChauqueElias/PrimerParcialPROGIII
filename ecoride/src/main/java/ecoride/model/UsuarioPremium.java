package ecoride.model;

public class UsuarioPremium extends Usuario {

    public UsuarioPremium(Long id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double aplicarDescuento(double monto) {
        return monto * 0.85;
    }
}
package ecoride.strategy;

public class TarjetaCredito implements Medio {

    @Override
    public String cobrar(double monto) {
        return "Pago con tarjeta de crédito por $" + monto;
    }
}

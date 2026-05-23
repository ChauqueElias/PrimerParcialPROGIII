package ecoride.service;

public class PagoService {

    public String procesarPago(String metodoPago, double monto) {

        return "Pago realizado con " + metodoPago + " por $" + monto;
    }
}
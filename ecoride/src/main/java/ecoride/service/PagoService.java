package ecoride.service;

import ecoride.strategy.Medio;
import ecoride.strategy.TarjetaCredito;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    private Medio medio;

    public PagoService() {
        this.medio = new TarjetaCredito();
    }

    public void setMedio(Medio medio) {
        this.medio = medio;
    }

    public String procesarPago(double monto) {
        return medio.cobrar(monto);
    }
}

package ecoride.strategy;

public class BilleteraVirtual implements Medio {

    @Override
    public String cobrar(double monto) {
        return "Pago con billetera virtual por $" + monto;
    }
}

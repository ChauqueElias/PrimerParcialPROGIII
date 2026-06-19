package ecoride.strategy;

public class CriterioEstandar implements CriterioTarifa {

    @Override
    public double calcular(double tarifaBase, int minutos) {
        return tarifaBase * minutos;
    }
}

package ecoride.strategy;

public class CriterioHoraPico implements CriterioTarifa {

    @Override
    public double calcular(double tarifaBase, int minutos) {
        double costoBase = tarifaBase * minutos;
        return costoBase * 1.40;
    }
}

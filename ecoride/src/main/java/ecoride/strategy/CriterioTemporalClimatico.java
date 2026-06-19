package ecoride.strategy;

public class CriterioTemporalClimatico implements CriterioTarifa {

    @Override
    public double calcular(double tarifaBase, int minutos) {
        double costoBase = tarifaBase * minutos;
        return costoBase + 150;
    }
}

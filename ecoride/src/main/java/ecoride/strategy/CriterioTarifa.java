package ecoride.strategy;

public interface CriterioTarifa {
    double calcular(double tarifaBase, int minutos);
}

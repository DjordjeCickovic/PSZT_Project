package FitnessFunctions;

import Algorithm.Phenotype;

public class RastriginFitnessFunction implements FitnessFunction<Phenotype> {

    @Override
    public boolean belongsToDomain(Phenotype phenotype) {
        return phenotype.getX1()>=-1 && phenotype.getX1()<=-1 &&
                phenotype.getX2()>=-1 && phenotype.getX2()<=1;
    }

    @Override
    public double applyAsDouble(Phenotype value) {

        if(!belongsToDomain(value))
            throw new IllegalArgumentException();

        double x1 = value.getX1();
        double x2 = value.getX2();

        return -(x1*x1 - Math.cos(18 * Math.PI*x1) + x2*x2 - Math.cos(18 * Math.PI*x2));
    }
}

package FitnessFunctions;

import Algorithm.Phenotype;

public class SchafferFitnessFunction implements FitnessFunction<Phenotype> {

    @Override
    public boolean belongsToDomain(Phenotype phenotype) {
        return phenotype.getX1()>=-10 && phenotype.getX1()<=10 &&
                phenotype.getX2()>=-10 && phenotype.getX2()<=10;
    }

    @Override
    public double getMinPhenotypeX1() {
        return -10;
    }
    @Override
    public double getMaxPhenotypeX1() {
        return 10;
    }
    @Override
    public double getMinPhenotypeX2() {
        return -10;
    }
    @Override
    public double getMaxPhenotypeX2() {return 10;}

    @Override
    public double applyAsDouble(Phenotype value) {

        if(!belongsToDomain(value))
            throw new IllegalArgumentException();

        double x1 = value.getX1();
        double x2 = value.getX2();

        return -( 0.5 + (Math.pow(x1*x1 - x2*x2,2)-0.5)/(Math.pow(1 + 0.001 * (x1*x1 + x2*x2),2))  );
    }
}

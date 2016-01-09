package FitnessFunctions;

import Algorithm.Phenotype;

public class MichalewiczFitnessFunction implements FitnessFunction<Phenotype> {

    @Override
    public boolean belongsToDomain(Phenotype phenotype) {
        return phenotype.getX1()>=0 && phenotype.getX1()<=Math.PI &&
                phenotype.getX2()>=0 && phenotype.getX2()<=Math.PI;
    }

    @Override
    public double getMinPhenotypeX1() {
        return 0;
    }
    @Override
    public double getMaxPhenotypeX1() {
        return Math.PI;
    }
    @Override
    public double getMinPhenotypeX2() {
        return 0;
    }
    @Override
    public double getMaxPhenotypeX2() {return Math.PI;}

    @Override
    public double applyAsDouble(Phenotype value) {

        if(!belongsToDomain(value))
            throw new IllegalArgumentException();

        double x1 = value.getX1();
        double x2 = value.getX2();

        return  ( Math.sin(x1)*Math.pow(Math.sin(1*Math.pow(x1,2/Math.PI)), 20)
                +  Math.sin(x2)*Math.pow(Math.sin(2*Math.pow(x2,2/Math.PI)), 20) );
    }
}

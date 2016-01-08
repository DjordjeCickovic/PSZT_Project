package Algorithm;

import FitnessFunctions.FitnessFunction;

import java.util.Set;

public class Population {

    Set<Phenotype> population;

    /**Function that allows to evaluate the adaptation of phenotype.*/
    FitnessFunction fitnessFunction;


    public Population(FitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    public double optimize() {throw new UnsupportedOperationException();}


    private void reproduce() {}

    private void chooseTheFittest() {}
}

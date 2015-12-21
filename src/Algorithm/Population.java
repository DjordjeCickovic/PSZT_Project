package Algorithm;

import java.util.Set;
import java.util.function.ToDoubleFunction;

public class Population {

    Set<Phenotype> population;

    /**Function that allows to evaluate the adaptation of phenotype.*/
    ToDoubleFunction<Phenotype> fitnessFunction;


    public Population(ToDoubleFunction<Phenotype> fitnessFunction) {}

    public double optimize() {throw new UnsupportedOperationException();}

    private void reproduce() {}

    private void chooseTheFittest() {}
}

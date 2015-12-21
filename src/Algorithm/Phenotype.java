package Algorithm;

import java.util.Set;

/**
 * Class represents single phenotype. It holds traits of individual from the population.
 */
public class Phenotype {


    public Phenotype(double x1, double x2, double sigma1,double sigma2) {}

    /**Randomly modify traits held by Phenotype.*/
    public void mutate() {}

    public double getX1() {throw new UnsupportedOperationException();}
    public double getX2() {throw new UnsupportedOperationException();}

    public static Set<Phenotype> crossingOver(Phenotype mom, Phenotype dad) {throw new UnsupportedOperationException();}
}

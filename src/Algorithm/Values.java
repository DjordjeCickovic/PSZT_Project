package Algorithm;

/**
 * This class holds optimal parameters that are used in  (lambda, mu) evolutionary algorithm
 * realized by OptimisationAlgorithm class. Their values were determined experimentally.
 */
public  class Values {

    /**Size of population.*/
    static final int MU = 600;
    /**Quantity of population children.*/
    static final int LAMBDA = 1500;
    /**How many iterations of algorithm*/
    static final int ITERATIONS = 100;

    static final double INITIAL_EVOLUTIONARY_PRESSURE = 0.7;
    static final double MAXIMAL_EVOLUTIONARY_PRESSURE = 1.5;

    /**The value of which evolutionary will be increased with each iteration.*/
    static final double EVOLUTIONARY_PRESSURE_DIFFERENCE = (MAXIMAL_EVOLUTIONARY_PRESSURE- INITIAL_EVOLUTIONARY_PRESSURE)/(double)ITERATIONS;
}

package Algorithm;

public  class Values {

    static final int MU = 600;
    static final int LAMBDA = 1500;
    static final int ITERATIONS = 100;

    static final double INITIAL_EVOLUTIONARY_PRESSURE = 0.7;
    static final double MAXIMAL_EVOLUTIONARY_PRESSURE = 1.5;

    static final double EVOLUTIONARY_PRESSURE_DIFFERENCE = (MAXIMAL_EVOLUTIONARY_PRESSURE- INITIAL_EVOLUTIONARY_PRESSURE)/(double)ITERATIONS;

}

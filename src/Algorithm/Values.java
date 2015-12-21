package Algorithm;

import java.util.function.ToDoubleFunction;
import java.lang.Math;

public  class Values {

    static final int MU = 44;
    static final int LAMBDA = 55;
    static final int ITERATIONS = 99;

    ToDoubleFunction<Phenotype> RASTRIGIN = phenotype -> {
        double x1 = phenotype.getX1();
        double x2 = phenotype.getX2();

        return -(x1*x1 - Math.cos(18 * Math.PI*x1) + x2*x2 - Math.cos(18 * Math.PI*x2));
    };

    ToDoubleFunction<Phenotype> SHAFFER = phenotype -> {
        throw new UnsupportedOperationException();
    };

    ToDoubleFunction<Phenotype> MICHALKIEWICZ = phenotype -> {
        throw new UnsupportedOperationException();
    };
}

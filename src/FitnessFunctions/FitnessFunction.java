package FitnessFunctions;

import java.util.function.ToDoubleFunction;

public interface FitnessFunction<Phenotype> extends ToDoubleFunction<Phenotype> {
    boolean belongsToDomain(Phenotype phenotype);

    double getMinPhenotypeX1();
    double getMaxPhenotypeX1();
    double getMinPhenotypeX2();
    double getMaxPhenotypeX2();

}

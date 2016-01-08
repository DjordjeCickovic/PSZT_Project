package FitnessFunctions;

import java.util.function.ToDoubleFunction;

public interface FitnessFunction<Phenotype> extends ToDoubleFunction<Phenotype> {
    boolean belongsToDomain(Phenotype phenotype);
}

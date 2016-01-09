import Algorithm.OptimisationAlgorithm;
import Algorithm.Phenotype;
import FitnessFunctions.MichalewiczFitnessFunction;
import FitnessFunctions.RastriginFitnessFunction;
import FitnessFunctions.SchafferFitnessFunction;

public class Main {
    public static void main(String [] args)
    {
        OptimisationAlgorithm alg = new OptimisationAlgorithm();

        alg.optimize(new RastriginFitnessFunction());
        Phenotype p = alg.getBestPhenotype().get();
        System.out.println("Rastrigin:");
        System.out.println("Ext: x1 = " + p.getX1() + "  x2 = " + p.getX2() +
                " | fitness function value:" + alg.getBestFitnessFunctionValue().get());

        alg.optimize(new SchafferFitnessFunction());
        p = alg.getBestPhenotype().get();
        System.out.println("Schafffer:");
        System.out.println("Ext: x1 = " + p.getX1() + "  x2 = " + p.getX2() +
                " | fitness function value:" + alg.getBestFitnessFunctionValue().get());

        alg.optimize(new MichalewiczFitnessFunction());
        p = alg.getBestPhenotype().get();
        System.out.println("Michalewicz:");
        System.out.println("Ext: x1 = " + p.getX1() +  "  x2 = " + p.getX2()+
                " | fitness function value:" + alg.getBestFitnessFunctionValue().get());
    }
}

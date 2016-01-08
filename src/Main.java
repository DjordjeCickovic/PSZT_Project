import Algorithm.Phenotype;
import Algorithm.Population;
import FitnessFunctions.RastriginFitnessFunction;

public class Main {
    public static void main(String [] args)
    {
        Population p = new Population(new RastriginFitnessFunction());
        Phenotype ph = p.optimize();
        System.out.println("Rastrigin:");
        System.out.println("Ext: x1 = " + ph.getX1() +  "  x2 = " + ph.getX2());
    }
}

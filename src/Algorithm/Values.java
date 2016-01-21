package Algorithm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

/**
 * This class holds optimal parameters that are used in  (lambda, mu) evolutionary algorithm
 * realized by OptimisationAlgorithm class. Their values were determined experimentally.
 */
public  class Values {

    private static Values instance = null;

    /**Size of population.*/
    private int mu = 600;
    /**Quantity of population children.*/
    private int lambda = 1500;
    /**How many iterations of algorithm*/
    private int iterations = 100;

    private double initialEvolutionaryPressure = 0.7;
    private double maximalEvolutionaryPressure = 2.1;

    private double mutationProbability = 0.9;
    private double mutationRange = 0.01;

    private Values() {}


    public static Values getInstance()
    {
        if(instance == null)
            instance = new Values();

        return instance;
    }

    public void loadConfiguration(String configurationFile) throws FileNotFoundException {

        Scanner scanner  = new Scanner(new FileInputStream(configurationFile));
        scanner.useLocale(Locale.ENGLISH);

        String option;

        while (scanner.hasNext())
        {
            option = scanner.next();

            switch (option)
            {
                case "mu" : mu = scanner.nextInt(); break;
                case "lambda" : lambda = scanner.nextInt(); break;
                case "iterations" : iterations = scanner.nextInt(); break;
                case "min_pressure" : initialEvolutionaryPressure = scanner.nextDouble(); break;
                case "max_pressure" : maximalEvolutionaryPressure = scanner.nextDouble(); break;
                case "mutationProbability" : mutationProbability = scanner.nextDouble(); break;
                case "mutationRange" : mutationRange = scanner.nextDouble(); break;
            }
        }
    }

    public int getMu() {
        return mu;
    }

    public int getLambda() {
        return lambda;
    }

    public int getIterations() {
        return iterations;
    }

    public double getInitialEvolutionaryPressure() {
        return initialEvolutionaryPressure;
    }

    public double getMaximalEvolutionaryPressure() {
        return maximalEvolutionaryPressure;
    }

    /**The value of which evolutionary will be increased with each iteration.*/
    public double getEvolutionaryPressureDifference() {
        return (maximalEvolutionaryPressure- initialEvolutionaryPressure)/(double)iterations;
    }

    public double getMutationProbability() {
        return mutationProbability;
    }

    public double getMutationRange() {
        return mutationRange;
    }
}

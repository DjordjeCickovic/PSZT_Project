package Algorithm;

import FitnessFunctions.FitnessFunction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class OptimisationAlgorithm {

    List<Double> bestFitnessFunctionHistory = new LinkedList<>();
    List<Double> averageFitnessFunctionHistory = new LinkedList<>();
    Optional<Phenotype> bestPhenotype = Optional.empty();
    Optional<Double> bestFitnessFunctionValue = Optional.empty();



    public void optimize(FitnessFunction<Phenotype> fitnessFunction) {

        int iterationsCounter = 0;
        double evolutionaryPressure = Values.INITIAL_EVOLUTIONARY_PRESSURE;
        Population muPopulation = new Population(fitnessFunction,Values.MU);
        Population lambdaPopulation;

        bestFitnessFunctionHistory = new LinkedList<>();
        averageFitnessFunctionHistory = new LinkedList<>();

        do {
            //Statistics.
            bestFitnessFunctionHistory.add(muPopulation.getBestFitnessFunctionValue());
            averageFitnessFunctionHistory.add(muPopulation.getAverageFitnessFunctionValue());

            //Choosing individuals that will reproduce.
            Population designated = muPopulation.designateNewPopulation(Values.LAMBDA);

            //Creating children.
            lambdaPopulation = designated.reproduce();

            //Choosing the fittest phenotypes that will be preserved in next generation.
            muPopulation = lambdaPopulation.chooseTheFittest(Values.MU,evolutionaryPressure);

            evolutionaryPressure+=Values.EVOLUTIONARY_PRESSURE_DIFFERENCE;
            iterationsCounter++;
        }
        while(iterationsCounter<Values.ITERATIONS);

        bestPhenotype = Optional.of(muPopulation.getBestPhenotype());
        bestFitnessFunctionValue = Optional.of(fitnessFunction.applyAsDouble(bestPhenotype.get()));
    }

    public List<Double> getBestFitnessFunctionHistory() {
        return bestFitnessFunctionHistory;
    }

    public List<Double> getAverageFitnessFunctionHistory() {
        return averageFitnessFunctionHistory;
    }

    public Optional<Phenotype> getBestPhenotype() {
        return bestPhenotype;
    }

    public Optional<Double> getBestFitnessFunctionValue() {
        return bestFitnessFunctionValue;
    }
}
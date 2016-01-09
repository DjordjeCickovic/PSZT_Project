package Algorithm;

import FitnessFunctions.FitnessFunction;

import java.util.*;

public class Population {

    private List<Phenotype> population;
    /**Function that allows to evaluate the adaptation of phenotype.*/
    private FitnessFunction<Phenotype> fitnessFunction;


    public Population(FitnessFunction<Phenotype> fitnessFunction,int populationSize) {
        this.fitnessFunction = fitnessFunction;
        this.population = new LinkedList<>();

        //Generating an initial population of phenotypes.
        Random random = new Random();
        for(int i = 0; i<populationSize; ++i)
        {
            population.add(new Phenotype(random.nextDouble()*(fitnessFunction.getMaxPhenotypeX1()-fitnessFunction.getMinPhenotypeX1())+fitnessFunction.getMinPhenotypeX1(),
                    random.nextDouble()*(fitnessFunction.getMaxPhenotypeX2()-fitnessFunction.getMinPhenotypeX2())+fitnessFunction.getMinPhenotypeX2(),
                    random.nextDouble()*(fitnessFunction.getMaxPhenotypeX1()-fitnessFunction.getMinPhenotypeX1())/5,
                    random.nextDouble()*(fitnessFunction.getMaxPhenotypeX2()-fitnessFunction.getMinPhenotypeX2())/5));
        }
    }

    private Population(FitnessFunction<Phenotype> fitnessFunction)
    {
        this.fitnessFunction = fitnessFunction;
        this.population = new LinkedList<>();
    }




    public Population designateNewPopulation(long newPopulationSize)
    {
        Population designated = new Population(fitnessFunction);
        for (int i = 0; i < newPopulationSize; ++i) {
            designated.population.add(getRandomPhenotype());
        }
        return designated;
    }

    public Population reproduce()
    {

        Population children = new Population(fitnessFunction);

        //Crossing parents.
        Iterator<Phenotype> iterator = population.iterator();
        for(int i = 0; i<population.size(); i+=2)
        {
            //Situation when there is odd number od phenotypes in reproducing population.
            if(i == population.size()-1)
            {
                Phenotype mom = iterator.next();
                Phenotype dad = population.iterator().next();
                children.population.addAll(Phenotype.crossingOver(mom, dad));
                break;
            }
            Phenotype mom = iterator.next();
            Phenotype dad = iterator.next();

            children.population.addAll(Phenotype.crossingOver(mom, dad));
        }

        //Mutating embryos
        children.population.stream().map(embryo ->
        {
            Phenotype mutatedEmbryo;
            do {
                mutatedEmbryo = new Phenotype(embryo.getX1(), embryo.getX2(), embryo.getSigma1(), embryo.getSigma2());
                mutatedEmbryo.mutate();
            }
            while (!fitnessFunction.belongsToDomain(mutatedEmbryo));
            return mutatedEmbryo;
        });

        return children;
    }

    public Population chooseTheFittest(int sizeOfNewPopulation, double evolutionaryPressure)
    {
        double averageFitnessValue = getAverageFitnessFunctionValue();
        //Temporary...
       // System.out.println(averageFitnessValue +" max:"+getBestFitnessFunctionValue() + " evolutionaryPressure " + evolutionaryPressure);

        double standardDeviation = 0;
        for(Phenotype p : population)
        {
            standardDeviation += Math.pow(fitnessFunction.applyAsDouble(p)-averageFitnessValue,2);
        }
        standardDeviation = standardDeviation/population.size();
        standardDeviation = Math.sqrt(standardDeviation);

        List<Double> roulette = new LinkedList<>();
        double rouletteSize = 0;
        for(Phenotype p : population)
        {
            double scaledFitnessFunctionValue = evolutionaryPressure*(fitnessFunction.applyAsDouble(p)-averageFitnessValue)/standardDeviation;
            double rouletteAssignation = Math.exp(scaledFitnessFunctionValue);
            roulette.add(rouletteAssignation);
            rouletteSize+=rouletteAssignation;
        }

        Random random  = new Random();
        Population chosen = new Population(fitnessFunction);
        for(int i = 0; i< sizeOfNewPopulation; ++i)
        {
            double randomValue = random.nextDouble()*rouletteSize;

            double sum = 0;
            Iterator<Double> rouletteIterator = roulette.iterator();
            Iterator<Phenotype> populationIterator = population.iterator();
            Phenotype p;

            do{
                p = populationIterator.next();
                sum+=rouletteIterator.next();
            }
            while(sum<randomValue && rouletteIterator.hasNext() && populationIterator.hasNext());

            chosen.population.add(p);
        }

        return chosen;
    }



    public Phenotype getBestPhenotype()
    {
        Iterator<Phenotype> iterator = population.iterator();
        Phenotype bestPhenotype =  population.iterator().next();
        while(iterator.hasNext())
        {
            Phenotype nextPhenotype = iterator.next();
            bestPhenotype = (fitnessFunction.applyAsDouble(nextPhenotype)>fitnessFunction.applyAsDouble(bestPhenotype))?nextPhenotype:bestPhenotype;
        }
        return bestPhenotype;
    }

    public double getBestFitnessFunctionValue()
    {
        return fitnessFunction.applyAsDouble(getBestPhenotype());
    }

    public double getAverageFitnessFunctionValue()
    {
        double sum = 0;
        for(Phenotype phenotype : population)
        {
            sum+=fitnessFunction.applyAsDouble(phenotype);
        }

        return sum/population.size();
    }

    private Phenotype getRandomPhenotype()
    {
        Random random = new Random();
        int randomElement = random.nextInt(population.size());
        int counter = 0;

        for(Phenotype element : population)
        {
            if(counter == randomElement)
                return element;
            ++counter;
        }

        return population.iterator().next(); //It will never happen.
    }
}

package Algorithm;

import FitnessFunctions.FitnessFunction;

import java.util.*;

public class Population {

    List<Phenotype> muPopulation = new LinkedList<>();
    /**Function that allows to evaluate the adaptation of phenotype.*/
    FitnessFunction<Phenotype> fitnessFunction;


    public Population(FitnessFunction<Phenotype> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;

        Random random = new Random();

        //Generating an initial population of phenotypes.
        for(int i = 0; i<Values.MU; ++i)
        {
            muPopulation.add(new Phenotype(random.nextDouble()*(fitnessFunction.getMaxPhenotypeX1()-fitnessFunction.getMinPhenotypeX1())+fitnessFunction.getMinPhenotypeX1(),
                                         random.nextDouble()*(fitnessFunction.getMaxPhenotypeX2()-fitnessFunction.getMinPhenotypeX2())+fitnessFunction.getMinPhenotypeX2(),
                                         random.nextDouble()*(fitnessFunction.getMaxPhenotypeX1()-fitnessFunction.getMinPhenotypeX1())/6,
                                         random.nextDouble()*(fitnessFunction.getMaxPhenotypeX2()-fitnessFunction.getMinPhenotypeX2())/6));
        }

    }

    public Phenotype optimize() {

        int iterationsCounter = 0;

        do {
            iterationsCounter++;
            //Choosing individuals that will reproduce.
            List<Phenotype> reproducingPopulation = new LinkedList<>();
            for (int i = 0; i < Values.LAMBDA; ++i) {
                reproducingPopulation.add(getRandomPhenotype(muPopulation));
            }

            //Generating embryos.
            List<Phenotype> lambdaPopulation = reproduce(reproducingPopulation);

            //Mutating embryos to get final lambda population.
            mutatePopulation(lambdaPopulation);

            //Choosing the fittest phenotypes that will be preserved in next generation.
            muPopulation = chooseTheFittest(lambdaPopulation);
        }
        while(iterationsCounter<Values.ITERATIONS);

        return getBestPhenotype(muPopulation);
    }


    private List<Phenotype> reproduce(List<Phenotype> reproducingPopulation)
    {
        List<Phenotype> embryons = new LinkedList<>();

        Iterator<Phenotype> iterator = reproducingPopulation.iterator();
        for(int i = 0; i<reproducingPopulation.size()-1; i+=2)
        {
            //Situation when there is odd number od phenotypes in reproducing population.
            if(i == reproducingPopulation.size()-1)
            {
                Phenotype mom = iterator.next();
                Phenotype dad = reproducingPopulation.iterator().next();
                embryons.addAll(Phenotype.crossingOver(mom,dad));
                break;
            }
            Phenotype mom = iterator.next();
            Phenotype dad = iterator.next();

            embryons.addAll(Phenotype.crossingOver(mom,dad));
        }

        return embryons;
    }

    private void mutatePopulation(List<Phenotype> embryos)
    {
        embryos.stream().map(embryo ->
        {
            Phenotype mutatedEmbryo;
            do {
                mutatedEmbryo = new Phenotype(embryo.getX1(), embryo.getX2(), embryo.getSigma1(), embryo.getSigma2());
                mutatedEmbryo.mutate();
            }
            while (!fitnessFunction.belongsToDomain(mutatedEmbryo));
            return mutatedEmbryo;
        });

    }

    //TODO Implement roulette algorithm here.
    private List<Phenotype> chooseTheFittest(List<Phenotype> lambdaPopulation)
    {
        List<Phenotype> newMuPopulation = new LinkedList<>();
        Iterator<Phenotype> iter = lambdaPopulation.iterator();
        double best = fitnessFunction.applyAsDouble(iter.next());
        newMuPopulation.add(iter.next());
        while(iter.hasNext())
        {
            Phenotype p  = iter.next();
            if(fitnessFunction.applyAsDouble(p)>best && newMuPopulation.size()<Values.MU-1)
            {
                 newMuPopulation.add(p);
            }
        }

        while(newMuPopulation.size()<Values.MU-1)
        {
            newMuPopulation.add(getRandomPhenotype(lambdaPopulation));
        }

        return newMuPopulation;
    }

    private double getBestFitnessFunctionValue(List<Phenotype> population)
    {
        Iterator<Phenotype> iterator = population.iterator();
        double bestFitnessFunctionValue = fitnessFunction.applyAsDouble(population.iterator().next());
        while(iterator.hasNext())
        {
            double nextValue =  fitnessFunction.applyAsDouble(iterator.next());
            bestFitnessFunctionValue = (nextValue>bestFitnessFunctionValue)?nextValue:bestFitnessFunctionValue;
        }
        return  bestFitnessFunctionValue;
    }

    private Phenotype getBestPhenotype(List<Phenotype> population)
    {
        Iterator<Phenotype> iterator = population.iterator();
        Phenotype bestPhenotype =  population.iterator().next();
        while(iterator.hasNext())
        {
            Phenotype nextPhenotype = iterator.next();
            bestPhenotype = (fitnessFunction.applyAsDouble(nextPhenotype)>fitnessFunction.applyAsDouble(bestPhenotype))?nextPhenotype:bestPhenotype;
        }
        return  bestPhenotype;
    }

    private Phenotype getRandomPhenotype(List<Phenotype> phenotypes)
    {
        Random random = new Random();
        int randomElement = random.nextInt(phenotypes.size());
        int counter = 0;

        for(Phenotype element : phenotypes)
        {
            if(counter == randomElement)
                return element;
            ++counter;
        }

        return phenotypes.iterator().next(); //It will never happen.
    }
}

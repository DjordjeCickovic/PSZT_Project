package Algorithm;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class represents single phenotype. It holds traits of individual from the population.
 */
public class Phenotype
{
	private X x;
	private Sigma sigma;

    public Phenotype(double x1, double x2, double sigma1, double sigma2) 
    {
    	x = new X(x1, x2);
    	sigma = new Sigma(sigma1, sigma2);
    }

    /**
     * Randomly modify traits held by Phenotype.
     */
    public void mutate() 
    {
        double tauPrime = 1/(Math.sqrt(2*2));
        double tau = 1/(Math.sqrt(2*Math.sqrt(2)));

        Random random = new Random();

        double xi = random.nextGaussian();

        double newSigma1 = getSigma1()*Math.exp( tauPrime*xi + tau*random.nextGaussian());
        double newSigma2 = getSigma2()*Math.exp( tauPrime*xi + tau*random.nextGaussian());

        double newX1 = getX1() + newSigma1*random.nextGaussian();
        double newX2 = getX2() + newSigma2*random.nextGaussian();

        setTraits(newX1, newX2, newSigma1, newSigma2);
    }

    public double getX1() 
    {
    	return x.x1;
    }
    
    public double getX2() 
    {
    	return x.x2;
    }
    
    public double getSigma1()
    {
    	return sigma.sigma1;
    }
    
    public double getSigma2()
    {
    	return sigma.sigma2;
    }
    
    /**
     * Function crosses given parents and returns their two children
     * @param mom first phenotype taking part in thr crossing
     * @param dad second phenotype taking part in crossing
     * @return children 
     */
    public static List<Phenotype> crossingOver(Phenotype mom, Phenotype dad)
    {       	
    	Phenotype ch1, ch2;
    	List<Phenotype> children = new LinkedList<>();
    	double a1, a2;
    	double x1, x2, sigma1, sigma2;
    
    	a1 = ThreadLocalRandom.current().nextDouble(0, 1);
    	a2 = 1 - a1;
    	    	
    	x1= a1*mom.getX1() + a2*dad.getX1();
    	x2= a1*mom.getX2() + a2*dad.getX2();
    	sigma1 = a1*mom.getSigma1() + a2*dad.getSigma2();
    	sigma2 = a1*mom.getSigma1() + a2*dad.getSigma2();
    	ch1 = new Phenotype(x1, x2, sigma1, sigma2);
    	
    	x1= a2*mom.getX1() + a1*dad.getX1();
    	x2= a2*mom.getX2() + a1*dad.getX2();
    	sigma1 = a2*mom.getSigma1() + a1*dad.getSigma2();
    	sigma2 = a2*mom.getSigma1() + a1*dad.getSigma2();
    	ch2 = new Phenotype(x1, x2, sigma1, sigma2);
    	
    	children.add(ch1);
    	children.add(ch2);
    	
    	return children;
    }

    private void setTraits(double x1, double x2, double sigma1, double sigma2)
    {
        this.x.x1 = x1;
        this.x.x2 = x2;
        this.sigma.sigma1 = sigma1;
        this.sigma.sigma2 = sigma2;
    }
}

class X
{
	double x1;
	double x2;
	
	X(double x1, double x2)
	{
		this.x1= x1;
		this.x2= x2;
	}
}

class Sigma
{
	double sigma1;
	double sigma2;
	
	Sigma(double sigma1, double sigma2)
	{
		this.sigma1= sigma1;
		this.sigma2= sigma2;
	}
}


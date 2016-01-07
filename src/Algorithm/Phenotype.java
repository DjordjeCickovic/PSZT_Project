package Algorithm;

import java.util.Set;
import java.util.TreeSet;
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
     * Funkcja krzyzujaca dwoch osobnikow i zwracajaca pare ich dzieci
     * @param mom - mama
     * @param dad - tata
     * @return children - para splodzonych dzieci
     */
    public static Set<Phenotype> crossingOver(Phenotype mom, Phenotype dad) 
    {       	
    	Phenotype ch1, ch2;
    	Set<Phenotype> children = new TreeSet<Phenotype>();
    	double a1, a2;
    	double x1, x2, sigma1, sigma2;
    	
    	// wyznaczenie liczb a1 i a2
    	a1 = ThreadLocalRandom.current().nextDouble(0, 1);
    	a2 = 1 - a1;
    	    	
    	x1= a1*mom.getX1() + a2*dad.getX1();
    	x2= a1*mom.getX2() + a2*dad.getX2();
    	sigma1 = a1*mom.getSigma1() + a2*dad.getSigma1();
    	sigma2 = a1*mom.getSigma1() + a2*dad.getSigma1();
    	ch1 = new Phenotype(x1, x2, sigma1, sigma2);
    	
    	x1= a2*mom.getX1() + a1*dad.getX1();
    	x2= a2*mom.getX2() + a1*dad.getX2();
    	sigma1 = a2*mom.getSigma1() + a1*dad.getSigma1();
    	sigma2 = a2*mom.getSigma1() + a1*dad.getSigma1();
    	ch2 = new Phenotype(x1, x2, sigma1, sigma2);
    	
    	children.add(ch1);
    	children.add(ch2);
    	
    	return children;
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


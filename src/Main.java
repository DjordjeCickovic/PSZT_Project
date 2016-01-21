import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.List;

import Algorithm.OptimisationAlgorithm;
import Algorithm.Phenotype;
import Algorithm.Values;
import FitnessFunctions.MichalewiczFitnessFunction;
import FitnessFunctions.RastriginFitnessFunction;
import FitnessFunctions.SchafferFitnessFunction;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Main {
    public static void main(String [] args)
    {
        
    	ApplicationFrame frame = new ApplicationFrame("Program");
        OptimisationAlgorithm alg = new OptimisationAlgorithm();

        if(args.length==0)
        	return;

        //Parsing parameters of optimisation algorithm.
        if(args.length==2)
            try {
                Values.getInstance().loadConfiguration(args[1]);
            } catch (FileNotFoundException e) {
                System.out.println("Configuration file not found!");
            }

        //Optimizing chosen function.
        switch(args[0])
        {
        case "Rastrigin": alg.optimize(new RastriginFitnessFunction()); break;
        case "Schaffer": alg.optimize(new SchafferFitnessFunction()); break;
        case "Michalewicz": alg.optimize(new MichalewiczFitnessFunction()); break;
            default: return;
        }

        System.out.println("Optimisation results:");
        System.out.println("x1~"+alg.getBestPhenotype().get().getX1());
        System.out.println("x2~"+alg.getBestPhenotype().get().getX2());
        System.out.println("Value of fitness function ~"+alg.getBestFitnessFunctionValue().get());

        frame.setContentPane(generateChart(alg.getBestFitnessFunctionHistory(),alg.getAverageFitnessFunctionHistory()));
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
        
    }
    
    public static ChartPanel generateChart(List<Double> BestValues,List<Double> AverageValues)
    {							
    	XYSeries Best = new XYSeries("Best");  
    	int i=0;							//A niechaj narodowie wzdy postronni znaja,
    	for (double value:BestValues)		//iz Polacy nie gesi, iz swoj jezyk maja (acz juz nie swoje znaki)
    	{
    		Best.add(i,value);
    		i++;
    	}
    	XYSeries Average = new XYSeries("Average");
    	i=0;
    	for (double value:AverageValues)
    	{
    		Average.add(i,value);
    		i++;
    	}
    	
    	
    	XYSeriesCollection dataset = new XYSeriesCollection();
    	dataset.addSeries(Best);
    	dataset.addSeries(Average);
    	
    	JFreeChart chart = ChartFactory.createXYLineChart(
    	"fitness over generations", // Title
    	"generation", // x-axis Label
    	"fitnes value", // y-axis Label
    	dataset, // Dataset
    	PlotOrientation.VERTICAL, // Plot Orientation
    	true, // Show Legend
    	true, // Use tooltips
    	false // Configure chart to generate URLs?
    	);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(800, 800));
    	return chartPanel;

    }
}

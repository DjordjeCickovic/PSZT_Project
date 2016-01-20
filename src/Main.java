import java.awt.Dimension;
import java.util.List;

import Algorithm.OptimisationAlgorithm;
import Algorithm.Phenotype;
import FitnessFunctions.MichalewiczFitnessFunction;
import FitnessFunctions.RastriginFitnessFunction;
import FitnessFunctions.SchafferFitnessFunction;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Main {
    public static void main(String [] args)
    {
    	
    	
        
        
        OptimisationAlgorithm alg = new OptimisationAlgorithm();

        alg.optimize(new RastriginFitnessFunction());
        Phenotype p = alg.getBestPhenotype().get();
        System.out.println("Rastrigin:");
        System.out.println("Ext: x1 = " + p.getX1() + "  x2 = " + p.getX2() +
                " | fitness function value:" + alg.getBestFitnessFunctionValue().get());
        
        
        ApplicationFrame frame = new ApplicationFrame("Program");
        
        frame.setContentPane(generateChart(alg.getBestFitnessFunctionHistory(),alg.getAverageFitnessFunctionHistory()));
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
        
        
        
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
        chartPanel.setPreferredSize(new Dimension(500, 500));
    	return chartPanel;

    }
}

import java.io.*;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;

public class PlotCharts
{

        public void plot(int i, ArrayList<Integer> qs_numberSize, ArrayList<Long> qs_timeUsed, ArrayList<Integer> hs_numberSize, ArrayList<Long> hs_timeUsed) throws Exception
        {
            final XYSeries quicksort = new XYSeries( "Quicksort" );
            final XYSeries heapsort = new XYSeries("Heapsort");
            long max = 0;
            for (int index = 0; index < qs_numberSize.size(); index++)
            {
                quicksort.add(qs_numberSize.get(index), qs_timeUsed.get(index));
                if (qs_timeUsed.get(index) > max)
                {
                    max = qs_timeUsed.get(index);
                }
            }
            for (int index = 0; index < hs_numberSize.size(); index++)
            {
                heapsort.add( hs_numberSize.get(index), hs_timeUsed.get(index));
                if (hs_timeUsed.get(index) > max)
                {
                    max = hs_timeUsed.get(index);
                }
            }
            final XYSeriesCollection dataset = new XYSeriesCollection( );
            dataset.addSeries(quicksort);
            dataset.addSeries(heapsort);

            JFreeChart xylineChart = ChartFactory.createXYLineChart(
                    "Sort statistics",
                    "Size of data",
                    "Time consumed",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false);

            XYPlot xyplot = (XYPlot) xylineChart.getPlot();
            NumberAxis xAxis = new NumberAxis();
            NumberAxis yAxis = new NumberAxis();
            xAxis.setTickUnit(new NumberTickUnit(1000000));
            xAxis.setRange(100000,10000000);
            yAxis.setRange(0, max + max * 0.1);
            xyplot.setDomainAxis(xAxis);
            xyplot.setRangeAxis(yAxis);
            int width = 1300; /* Width of the image */
            int height = 1000; /* Height of the image */
            File XYChart = new File( "XYLineChart" + i + ".jpeg" );
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }
}

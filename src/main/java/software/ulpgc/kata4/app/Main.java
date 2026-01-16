package software.ulpgc.kata4.app;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import software.ulpgc.kata4.model.Movie;
import software.ulpgc.kata4.tasks.HistogramBuilder;
import software.ulpgc.kata4.viewmodel.Histogram;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Main extends JFrame {

    private static final String url = "https://datasets.imdbws.com/title.basics.tsv.gz";

    public Main() {
        this.setTitle("Histogram Display");
        this.setSize(800,600);
        this.setLocationRelativeTo((Component) null);
        this.setDefaultCloseOperation(3);
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.display(histogramOf(movies()));
        main.setVisible(true);
    }

    private void display(Histogram histogram) {
        this.getContentPane().add(this.displayOf(histogram));
        this.revalidate();
    }

    private Component displayOf(Histogram histogram) {
        return new ChartPanel(this.decorate(this.chartOf(histogram)));
    }

    private JFreeChart decorate(JFreeChart chart) {
        return chart;
    }

    private JFreeChart chartOf(Histogram histogram) {
        return ChartFactory.createHistogram(histogram.title(), histogram.x(), "count", this.dataserOf(histogram));
    }

    private XYSeriesCollection dataserOf(Histogram histogram) {
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(this.seriesOf(histogram));
        return collection;
    }

    private XYSeries seriesOf(Histogram histogram) {
        XYSeries series = new XYSeries(histogram.legend());
        Iterator var3 = histogram.iterator();

        while(var3.hasNext()) {
            int bin = (Integer) var3.next();
            series.add((double)bin, (double)histogram.count(bin));
        }

        return series;
    }

    private static Stream<Movie> movies() {
        return (new RemoteMovieLoader(url, MovieDeserializer::fromTsv).loadAll()).limit(1000L).filter((m) -> {
            return m.year() >= 1900;
        }).filter((m) -> {
            return m.year() <= 2025;
        });
    }

    private static Histogram histogramOf(Stream<Movie> movies) {
        return HistogramBuilder.with(movies).title("Películas por año").x("Año").legend("Nº películas").build(Movie::year);
    }
}

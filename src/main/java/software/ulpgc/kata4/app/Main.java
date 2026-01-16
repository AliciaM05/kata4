package software.ulpgc.kata4.app;

import software.ulpgc.kata4.model.Movie;
import software.ulpgc.kata4.tasks.HistogramBuilder;
import software.ulpgc.kata4.viewmodel.Histogram;

import java.util.List;

public class Main {

    private static final String url = "https://datasets.imdbws.com/title.basics.tsv.gz";

    public static void main(String[] args) {
        List<Movie> movies = new RemoteMovieLoader(url, MovieDeserializer::fromTsv).loadAll();
        display(new HistogramBuilder(Movie::year).build(movies));
        display(new HistogramBuilder(m -> m.duration() / 60).build(movies));
    }

    private static void display(Histogram histogram) {
        for (int key : histogram)
            System.out.println(key + " " + histogram.count(key));
    }
}

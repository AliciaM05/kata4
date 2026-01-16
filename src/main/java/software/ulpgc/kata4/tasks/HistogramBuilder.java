package software.ulpgc.kata4.tasks;

import software.ulpgc.kata4.viewmodel.Histogram;
import software.ulpgc.kata4.model.Movie;

import java.util.List;
import java.util.function.Function;

public class HistogramBuilder {
    private final Function<Movie, Integer> axis;

    public HistogramBuilder(Function<Movie, Integer> axis) {
        this.axis = axis;
    }

    public Histogram build(List<Movie> movies) {
        Histogram histogram = new Histogram();
        for (Movie movie : movies)
            histogram.add((int) axis.apply(movie));
        return histogram;

    }
}


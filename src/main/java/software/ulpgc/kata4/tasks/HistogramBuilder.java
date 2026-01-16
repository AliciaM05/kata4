package software.ulpgc.kata4.tasks;

import software.ulpgc.kata4.viewmodel.Histogram;
import software.ulpgc.kata4.model.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class HistogramBuilder {
    private final Stream<Movie> movies;
    private final Map<String, String> labels;

    public HistogramBuilder(Stream<Movie> movies) {
        this.movies = movies;
        this.labels = new HashMap<>();
    }

    public static HistogramBuilder with(Stream<Movie> movies) {
        return new HistogramBuilder(movies);
    }

    public HistogramBuilder title(String title) {
        this.labels.put("title", title);
        return this;
    }

    public HistogramBuilder x(String x) {
        this.labels.put("x", x);
        return this;
    }

    public HistogramBuilder legend(String legend) {
        this.labels.put("legend", legend);
        return this;
    }

    public Histogram build(Function<Movie, Integer> binarize) {
        Histogram histogram = new Histogram(this.labels);
        Stream var1000 = this.movies.map(binarize);
        Objects.requireNonNull(histogram);
        var1000.forEach(bin -> histogram.add((Integer) bin));
        return histogram;
    }
}


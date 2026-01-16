package software.ulpgc.kata4.io;

import java.util.stream.Stream;
import software.ulpgc.kata4.model.Movie;

public interface MovieLoader {
    Stream<Movie> loadAll();
}


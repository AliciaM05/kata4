package software.ulpgc.kata4.app;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import software.ulpgc.kata4.io.MovieLoader;
import software.ulpgc.kata4.model.Movie;

public class RemoteMovieLoader implements MovieLoader {
    private final String url;
    private final Function<String, Movie> deserialize;

    public RemoteMovieLoader(String url, Function<String, Movie> deserialize) {
        this.url = url;
        this.deserialize = deserialize;
    }

    @Override
    public Stream<Movie> loadAll() {
        try {
            return this.loadFrom(new URL(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<Movie> loadFrom(URL url) throws IOException {
        return this.loadFrom(url.openConnection());
    }

    private Stream<Movie> loadFrom(URLConnection connection) throws IOException {
        return this.loadFrom(this.unzip(connection.getInputStream()));
    }

    private Stream<Movie> loadFrom(InputStream inputStream) {
        return this.loadFrom(this.toReader(inputStream));
    }

    private Stream<Movie> loadFrom(BufferedReader reader) {
        return reader.lines().skip(1L).map(this.deserialize);
    }

    private BufferedReader toReader(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private InputStream unzip(InputStream inputStream) throws IOException {
        return new GZIPInputStream(new BufferedInputStream(inputStream));
    }
}

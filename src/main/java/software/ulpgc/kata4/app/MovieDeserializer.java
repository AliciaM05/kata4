package software.ulpgc.kata4.app;

import software.ulpgc.kata4.model.Movie;

public class MovieDeserializer {
    public static Movie fromTsv(String str) {
        return fromTsv(str.split("\t"));
    }

    private static Movie fromTsv(String[] split) {
        return new Movie(split[2], toInt(split[5]), toInt(split[7]));
    }

    private static int toInt(String str) {
        return str.equals("\\N") ? -1 : Integer.parseInt(str);
    }
}

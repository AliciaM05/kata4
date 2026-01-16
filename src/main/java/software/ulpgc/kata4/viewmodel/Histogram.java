package software.ulpgc.kata4.viewmodel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Histogram implements Iterable<Integer> {
    private final Map<Integer, Integer> frequencies;

    public Histogram() {
        this.frequencies = new HashMap<>();
    }

    public void add(int bin) {
        frequencies.put(bin, count(bin) + 1);
    }

    public int count(int bin) {
        return frequencies.getOrDefault(bin, 0);
    }

    @Override
    public Iterator<Integer> iterator() {
        return frequencies.keySet().iterator();
    }

    public int size() {
        return frequencies.size();
    }
}


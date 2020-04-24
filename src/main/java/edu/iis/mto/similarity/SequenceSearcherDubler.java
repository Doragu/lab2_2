package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherDubler implements SequenceSearcher {

    private int[] args;

    private int timesCalledCounter = 0;

    public void setArgs(int[] args) {
        this.args = args;
    }

    public SearchResult search(int elem, int[] seq) {

        if (seq == null) {
            throw new IllegalArgumentException();
        }

        SearchResult.Builder builder = SearchResult.builder();

        int key = -1;
        if (args != null && args.length > 0) {
            key = args[timesCalledCounter];
        }

        builder.withFound(false);
        if (key != -1) {
            builder.withFound(true);
            builder.withPosition(key);
        }

        timesCalledCounter++;

        return builder.build();
    }

    public int getTimesUsedCounter() {
        return timesCalledCounter;
    }
}

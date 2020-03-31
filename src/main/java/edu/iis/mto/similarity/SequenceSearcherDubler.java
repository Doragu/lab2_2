package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherDubler implements SequenceSearcher {

    private int timesCalledCounter = 0;

    public SearchResult search(int elem, int[] seq) {

        if (seq == null || seq.length == 0) {
            throw new IllegalArgumentException();
        }

        timesCalledCounter++;
        SearchResult.Builder builder = SearchResult.builder();
        int i = 0;

        builder.withFound(false);

        for (int item : seq) {
            if (item == elem) {
                builder.withPosition(i);
                builder.withFound(true);
                break;
            }
            i++;
        }

        return builder.build();
    }

    public int getTimesUsedCounter() {
        return timesCalledCounter;
    }
}

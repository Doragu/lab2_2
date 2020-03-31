package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimilarityFinderBehaviorTests {

    private SequenceSearcherDubler sequenceSearcher;
    private SimilarityFinder similarityFinder;

    @BeforeEach void createNewSequenceSearcher() {
        sequenceSearcher = new SequenceSearcherDubler();
        similarityFinder = new SimilarityFinder(sequenceSearcher);
    }

    @Test void testWithTwoNullSequences() {
        final int seq1[] = null, seq2[] = null;

        Assertions.assertThrows(NullPointerException.class, () -> {
            similarityFinder.calculateJackardSimilarity(seq1, seq2);
        });
    }

    @Test void testWithOneNullSequence() {
        final int seq1[] = {1, 2, 3}, seq2[] = null;

        Assertions.assertThrows(NullPointerException.class, () -> {
            similarityFinder.calculateJackardSimilarity(seq1, seq2);
        });
    }

    @Test void testWithTwoFilledSequences() {
        final int seq1[] = {1, 2, 3}, seq2[] = {2, 0, -4};

        Assertions.assertDoesNotThrow(() -> {
            similarityFinder.calculateJackardSimilarity(seq1, seq2);
        });
    }

    @Test void testWithTwoEmptySequences() {
        final int seq1[] = {}, seq2[] = {};

        Assertions.assertDoesNotThrow(() -> {
            similarityFinder.calculateJackardSimilarity(seq1, seq2);
        });
    }

    @Test void testWithNullSequenceSearcher() {
        final int seq1[] = {2, 1}, seq2[] = {1, 2};

        similarityFinder = new SimilarityFinder(null);

        Assertions.assertThrows(NullPointerException.class, () -> {
            similarityFinder.calculateJackardSimilarity(seq1, seq2);
        });
    }

    @Test void testIfSequenceSearcherDublerWasUsed() {
        final int seq1[] = {2, 1}, seq2[] = {1, 2};

        similarityFinder.calculateJackardSimilarity(seq1, seq2);

        Assertions.assertNotEquals(sequenceSearcher.getTimesUsedCounter(), 0);
    }

}

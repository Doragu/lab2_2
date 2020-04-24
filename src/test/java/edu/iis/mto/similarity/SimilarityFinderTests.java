package edu.iis.mto.similarity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimilarityFinderTests {

    private final static int[] ORIGIN_SEQUENCE = {1, 2, 3};
    private final static int[] SEQUENCE_ONE_DIFF_ELEM = {1, 2, 4};
    private final static int[] SEQUENCE_ONE_SAME_ELEM = {1, -5, 4};
    private final static int[] SEQUENCE_ALL_DIFF_ELEM = {0, 11, -33};
    private final static int[] EMPTY_SEQUENCE = {};

    private SequenceSearcherDubler sequenceSearcher;
    private SimilarityFinder similarityFinder;

    @BeforeEach void createNewSequenceSearcher() {
        sequenceSearcher = new SequenceSearcherDubler();
        similarityFinder = new SimilarityFinder(sequenceSearcher);
    }

    @Test void testTwoIdenticalSequences() {
        final double EXPECTED_RESULT = 1.00;

        Assertions.assertEquals(similarityFinder.calculateJackardSimilarity(ORIGIN_SEQUENCE, ORIGIN_SEQUENCE.clone()), EXPECTED_RESULT);
    }

    @Test void testSequencesWithOneDifferentElement() {
        final double EXPECTED_RESULT = 0.5;

        Assertions.assertEquals(similarityFinder.calculateJackardSimilarity(ORIGIN_SEQUENCE, SEQUENCE_ONE_DIFF_ELEM), EXPECTED_RESULT);
    }

    @Test void testSequencesWithOneSameElement() {
        final double EXPECTED_RESULT = 0.2;

        Assertions.assertEquals(similarityFinder.calculateJackardSimilarity(ORIGIN_SEQUENCE, SEQUENCE_ONE_SAME_ELEM), EXPECTED_RESULT);
    }

    @Test void testSequencesWithZeroSameElements() {
        final double EXPECTED_RESULT = 0.0;

        Assertions.assertEquals(similarityFinder.calculateJackardSimilarity(ORIGIN_SEQUENCE, SEQUENCE_ALL_DIFF_ELEM), EXPECTED_RESULT);
    }

    @Test void testWithTwoEmptySequences() {
        final double EXPECTED_RESULT = 1.0;

        Assertions.assertEquals(similarityFinder.calculateJackardSimilarity(EMPTY_SEQUENCE, EMPTY_SEQUENCE.clone()), EXPECTED_RESULT);
    }

    @Test void testWithOneEmptySequence() {
        final double EXPECTED_RESULT = 0.0;

        Assertions.assertEquals(similarityFinder.calculateJackardSimilarity(ORIGIN_SEQUENCE, EMPTY_SEQUENCE), EXPECTED_RESULT);
    }

    @Test void testIfThrowsExceptionWithTwoNullSequences() {
        final int seq1[] = null, seq2[] = null;

        Assertions.assertThrows(NullPointerException.class, () -> {
            similarityFinder.calculateJackardSimilarity(seq1, seq2);
        });
    }

    @Test void testIfThrowsExceptionWithOneNullSequence() {
        final int seq1[] = {1, 2, 3}, seq2[] = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            similarityFinder.calculateJackardSimilarity(seq1, seq2);
        });
    }

    @Test void testIfThrowsExceptionWithTwoFilledSequences() {
        final int seq1[] = {1, 2, 3}, seq2[] = {2, 0, -4};

        Assertions.assertDoesNotThrow(() -> {
            similarityFinder.calculateJackardSimilarity(seq1, seq2);
        });
    }

    @Test void testIfThrowsExceptionWithTwoEmptySequences() {
        final int seq1[] = {}, seq2[] = {};

        Assertions.assertDoesNotThrow(() -> {
            similarityFinder.calculateJackardSimilarity(seq1, seq2);
        });
    }

    @Test void testIfThrowsExceptionWithNullSequenceSearcher() {
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

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
    private final static int[] NULL_SEQUENCE = null;

    private SimilarityFinder similarityFinder;
    private SequenceSearcherDubler sequenceSearcher;

    @BeforeEach void initialize() {
        sequenceSearcher = new SequenceSearcherDubler();
        similarityFinder = new SimilarityFinder(sequenceSearcher);
    }

    @Test void testTwoIdenticalSequences() {
        sequenceSearcher.setArgs(new int[] {0, 1, 2});

        final double EXPECTED_RESULT = 1.00;

        Assertions.assertEquals(similarityFinder.calculateJackardSimilarity(ORIGIN_SEQUENCE, ORIGIN_SEQUENCE.clone()), EXPECTED_RESULT);
    }

    @Test void testSequencesWithOneDifferentElement() {
        sequenceSearcher.setArgs(new int[] {0, 1, -1});

        final double EXPECTED_RESULT = 0.5;

        Assertions.assertEquals(similarityFinder.calculateJackardSimilarity(ORIGIN_SEQUENCE, SEQUENCE_ONE_DIFF_ELEM), EXPECTED_RESULT);
    }

    @Test void testSequencesWithOneSameElement() {
        sequenceSearcher.setArgs(new int[] {0, -1, -1});

        final double EXPECTED_RESULT = 0.2;

        Assertions.assertEquals(similarityFinder.calculateJackardSimilarity(ORIGIN_SEQUENCE, SEQUENCE_ONE_SAME_ELEM), EXPECTED_RESULT);
    }

    @Test void testSequencesWithZeroSameElements() {
        sequenceSearcher.setArgs(new int[] {-1, -1, -1});

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
        Assertions.assertThrows(NullPointerException.class, () -> {
            similarityFinder.calculateJackardSimilarity(NULL_SEQUENCE, NULL_SEQUENCE);
        });
    }

    @Test void testIfThrowsExceptionWithOneNullSequence() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            similarityFinder.calculateJackardSimilarity(ORIGIN_SEQUENCE, NULL_SEQUENCE);
        });
    }

    @Test void testIfThrowsExceptionWithTwoFilledSequences() {
        Assertions.assertDoesNotThrow(() -> {
            similarityFinder.calculateJackardSimilarity(ORIGIN_SEQUENCE, SEQUENCE_ALL_DIFF_ELEM);
        });
    }

    @Test void testIfThrowsExceptionWithTwoEmptySequences() {
        Assertions.assertDoesNotThrow(() -> {
            similarityFinder.calculateJackardSimilarity(EMPTY_SEQUENCE, EMPTY_SEQUENCE);
        });
    }

    @Test void testIfThrowsExceptionWithNullSequenceSearcher() {
        similarityFinder = new SimilarityFinder(null);

        Assertions.assertThrows(NullPointerException.class, () -> {
            similarityFinder.calculateJackardSimilarity(ORIGIN_SEQUENCE, SEQUENCE_ONE_DIFF_ELEM);
        });
    }

    @Test void testIfSequenceSearcherDublerWasUsed() {
        similarityFinder.calculateJackardSimilarity(SEQUENCE_ALL_DIFF_ELEM, SEQUENCE_ONE_SAME_ELEM);

        Assertions.assertNotEquals(sequenceSearcher.getTimesUsedCounter(), 0);
    }
}

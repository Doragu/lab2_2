package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimilarityFinderStateTests {

    private final static int[] ORIGIN_SEQUENCE = {1, 2, 3};
    private final static int[] SEQUENCE_ONE_DIFF_ELEM = {1, 2, 4};
    private final static int[] SEQUENCE_ONE_SAME_ELEM = {1, -5, 4};
    private final static int[] SEQUENCE_ALL_DIFF_ELEM = {0, 11, -33};

    private SequenceSearcher sequenceSearcher;
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

    }

    @Test void testWithOneEmptySequence() {

    }
}

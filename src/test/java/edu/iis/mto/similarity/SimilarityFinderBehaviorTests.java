package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimilarityFinderBehaviorTests {

    private SimilarityFinder similarityFinder;

    @BeforeEach void createNewSequenceSearcher() {
        SequenceSearcher sequenceSearcher = new SequenceSearcherDubler();
        similarityFinder = new SimilarityFinder(sequenceSearcher);
    }

    @Test void testWithTwoNullSequences() {
        final int seq1[] = null, seq2[] = null;

        Assertions.assertThrows(NullPointerException.class, () -> {
            similarityFinder.calculateJackardSimilarity(seq1, seq2);
        });
    }

    @Test void testWithOneNullSequence() {

    }

    @Test void testWithTwoFilledSequences() {

    }

    @Test

    void testWithTwoEmptySequences() {

    }

    @Test void testWithNullSequenceSearcher() {

    }

}

package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimilarityFinderStateTests {

    private final static int[] ORIGIN_SEQUENCE = {1, 2, 3};

    private SequenceSearcher sequenceSearcher;
    private SimilarityFinder similarityFinder;

    @BeforeEach void createNewSequenceSearcher() {
        sequenceSearcher = new SequenceSearcherDubler();
        similarityFinder = new SimilarityFinder(sequenceSearcher);
    }

    @Test void testTwoIdenticalSequences() {
        Assertions.assertEquals(similarityFinder.calculateJackardSimilarity(ORIGIN_SEQUENCE, ORIGIN_SEQUENCE.clone()), 1);
    }

    @Test void testSequencesWithOneDifferentElement() {

    }

    @Test void testSequencesWithOneSameElement() {

    }

    @Test void testSequencesWithZeroSameElements() {

    }

    @Test void testWithTwoEmptySequences() {

    }

    @Test void testWithOneEmptySequence() {

    }
}

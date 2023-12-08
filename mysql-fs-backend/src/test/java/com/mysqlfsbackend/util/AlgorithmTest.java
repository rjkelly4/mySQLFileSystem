package com.mysqlfsbackend.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlgorithmTest {
    @Test
    void binSearch_FindingTarget() {
        List<Integer> sortedList = Arrays.asList(-12, 2, 3, 4, 7, 8, 9, 100);
        assertTrue(Algorithm.binSearch(sortedList, 8));
        assertFalse(Algorithm.binSearch(sortedList, 5));
        assertFalse(Algorithm.binSearch(List.of(), 5));
    }

    @Test
    void binSearch_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Algorithm.binSearch(null, 5));

        List<Integer> sortedList = Arrays.asList(1, 2, 3, 4, 5);
        assertThrows(NullPointerException.class, () -> Algorithm.binSearch(sortedList, null));
    }

    @Test
    void testMergeSortedLists() {
        // Test when both lists are sorted in ascending order
        List<Integer> listA = Arrays.asList(1, 3, 5, 7);
        List<Integer> listB = Arrays.asList(2, 4, 6, 8);
        List<Integer> merged = Algorithm.merge(listA, listB, Comparator.naturalOrder());

        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), merged);
    }

    @Test
    void testMergeDescendingLists() {
        // Test when both lists are sorted in descending order
        List<Integer> listA = Arrays.asList(8, 6, 4, 2);
        List<Integer> listB = Arrays.asList(7, 5, 3, 1);
        List<Integer> merged = Algorithm.merge(listA, listB, Comparator.reverseOrder());

        assertEquals(Arrays.asList(8, 7, 6, 5, 4, 3, 2, 1), merged);
    }

    @Test
    void testMergeEmptyList() {
        // Test when one of the lists is empty
        List<Integer> listA = Arrays.asList(1, 3, 5, 7);
        List<Integer> listB = List.of();
        List<Integer> merged = Algorithm.merge(listA, listB, Comparator.naturalOrder());

        assertEquals(Arrays.asList(1, 3, 5, 7), merged);
    }
}

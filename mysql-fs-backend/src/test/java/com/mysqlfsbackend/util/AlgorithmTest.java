package com.mysqlfsbackend.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlgorithmTest {
    @Test
    void binSearch_FindingTarget() {
        List<Integer> sortedList = Arrays.asList(-12, 2, 3, 4, 7, 8, 9, 100);
        assertTrue(Algorithm.binSearch(sortedList, 8));
        assertFalse(Algorithm.binSearch(sortedList, 5));

        sortedList.clear();
        assertFalse(Algorithm.binSearch(sortedList, 5));
    }

    @Test
    void binSearch_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Algorithm.binSearch(null, 5));

        List<Integer> sortedList = Arrays.asList(1, 2, 3, 4, 5);
        assertThrows(NullPointerException.class, () -> Algorithm.binSearch(sortedList, null));
    }
}

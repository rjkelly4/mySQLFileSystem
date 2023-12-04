package com.mysqlfsbackend.util;

import java.util.List;

/**
 * This class defines custom static algorithms.
 */
public class Algorithm {
    /**
     * A binary search method that checks if a specified target is found in
     * a given sorted list.
     *
     * @param list A sorted list that will be searched.
     * @param target The item that will be searched in the list.
     * @return true if the target is found, otherwise false.
     * @param <T> Must be a comparable type.
     */
    public static <T extends Comparable<T>> boolean binSearch(List<T> list, T target) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int compare = list.get(mid).compareTo(target);

            if (compare == 0) {
                return true;
            }
            if (compare < 0) {
                left = mid + 1;
            }
            if (compare > 0) {
                right = mid - 1;
            }
        }

        return false;
    }
}

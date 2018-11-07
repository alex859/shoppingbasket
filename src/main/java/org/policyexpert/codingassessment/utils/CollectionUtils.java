package org.policyexpert.codingassessment.utils;

import java.util.List;

public class CollectionUtils {
    private CollectionUtils() {}

    // Lazy to include CollectionUtils
    public static <T> boolean isEmpty(final List<T> list) {
        return list == null || list.isEmpty();
    }
}

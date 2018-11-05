package org.policyexpert.codingassessment.utils;

import java.util.Objects;

/**
 * Collection of functions to validate object fields
 */
public class ValidationUtils {
    private ValidationUtils() {}

    /**
     * Checks that the given parameter is not null.
     *
     * @param object     The object to test.
     * @param objectName The object name.
     * @throws IllegalArgumentException When object is null.
     */
    public static <T> T notNull(final T object, final String objectName) {
        if (Objects.isNull(object)) {
            throw new IllegalArgumentException(String.format("%s cannot be null.", objectName));
        }

        return object;
    }
}

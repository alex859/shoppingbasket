package org.policyexpert.codingassessment.utils;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Collection of functions to validate object fields
 */
public class ValidationUtils {
    private ValidationUtils() {
    }

    /**
     * Checks that the given parameter is not null.
     *
     * @param object     The object to test.
     * @param objectName The object name.
     * @throws IllegalArgumentException When object is null.
     */
    public static <T> T notNull(final T object, final String objectName) {
        return validate(object, Objects::nonNull, objectName, "cannot be null");
    }

    /**
     * Checks that the given parameter is an empty String.
     *
     * @param strToTest  The string to test.
     * @param objectName The object name.
     * @throws IllegalArgumentException When the string is empty.
     */
    public static String notEmpty(final String strToTest, final String objectName) {
        return validate(strToTest, str -> str != null && !str.isEmpty(), objectName, "cannot be empty");
    }

    /**
     * Checks that the given predicate is valid for the given object.
     *
     * @throws IllegalArgumentException When the predicate is not valid for the object.
     */
    public static <T> T validate(final T obj, final Predicate<T> predicate, final String objectName, final String validationMessage) {
        if (predicate == null) {
            throw new IllegalArgumentException("Predicate cannot be null.");
        }
        if (!predicate.test(obj)) {
            throw new IllegalArgumentException(String.format("%s %s.", objectName, validationMessage));
        }

        return obj;
    }

    public static BigDecimal isPositiveOrZero(final BigDecimal number, final String objectName) {
        return validate(number, num -> Objects.nonNull(num) && num.compareTo(BigDecimal.ZERO) >= 0, objectName, "must be positive or zero");
    }

    public static BigDecimal readBigDecimal(final String str) {
        try {
            return new BigDecimal(str);
        } catch (final Exception ex) {
            throw new IllegalArgumentException(String.format("Unable to parse \"%s\" as a number.", str), ex);
        }
    }
}

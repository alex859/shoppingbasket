package org.policyexpert.codingassessment.utils;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.BigDecimalAssert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ValidationUtilsTest {

    @Test
    public void notNull_WHEN_ArgumentIsNull_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtils.notNull(null, "Item"))
                .withMessage("Item cannot be null.");
    }

    @Test
    public void notNull_WHEN_ArgumentIsNotNull_THEN_ShouldReturnSameObject() {
        final BigDecimal number = new BigDecimal("0.25");
        final BigDecimal result = ValidationUtils.notNull(number, "Number");
        Assertions.assertThat(result).isSameAs(number);
    }

    @Test
    public void validate_WHEN_PredicateIsNull_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtils.validate("", null, "Item", "not valid"))
                .withMessage("Predicate cannot be null.");
    }


    @Test
    public void validate_WHEN_PredicateReturnsFalse_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtils.validate("Not Empty", String::isEmpty, "Item", "is not valid"))
                .withMessage("Item is not valid.");
    }

    @Test
    public void validate_WHEN_PredicateReturnsTrue_THEN_AllGood() {
        final String result = ValidationUtils.validate("", String::isEmpty, "Item", "is not valid");
        Assertions.assertThat(result).isEqualTo("");
    }

    @Test
    public void isPositiveOrZero_WHEN_NegativeNumber_THEN_ShouldThrowIllegalArgumentException() {
        final BigDecimal negativeNumber = new BigDecimal("-0.25");
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtils.isPositiveOrZero(negativeNumber, "Number"))
                .withMessage("Number must be positive or zero.");
    }

    @Test
    public void isPositiveOrZero_WHEN_PositiveNumber_THEN_ShouldReturnSameNumber() {
        final BigDecimal positiveNumber = new BigDecimal("0.25");
        final BigDecimal result = ValidationUtils.isPositiveOrZero(positiveNumber, "Number");
        Assertions.assertThat(result).isSameAs(positiveNumber);
    }

    @Test
    public void isPositiveOrZero_WHEN_ZeroNumber_THEN_ShouldReturnSameNumber() {
        final BigDecimal zero = new BigDecimal("0.0");
        final BigDecimal result = ValidationUtils.isPositiveOrZero(zero, "Number");
        Assertions.assertThat(result).isSameAs(zero);
    }

    @Test
    public void readBigDecimal_WHEN_NotANumber_THEN_ShouldThrowIllegalArgumentException() {
        final String notANumber = "Not a number";
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtils.readBigDecimal(notANumber))
                .withMessage("Unable to parse \"Not a number\" as a number.");
    }

    @Test
    public void readBigDecimal_WHEN_IsANumber_ShouldReturnCorrectBigDecimal() {
        final String validNumber = "0.05";
        final BigDecimal bigDecimal = ValidationUtils.readBigDecimal(validNumber);
        Assertions.assertThat(bigDecimal).isEqualByComparingTo(validNumber);
    }
}
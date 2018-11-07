package org.policyexpert.codingassessment.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeightBasedProductTest {

    @Test
    public void builder_WHEN_NullCode_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() ->
                WeightBasedProduct.builder().pricePerKg("2.0").weightInKg("2").build()
        );
    }

    @Test
    public void builder_WHEN_EmptyCode_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() ->
                WeightBasedProduct.builder().pricePerKg("2.0").weightInKg("2").code("").build()
        );
    }

    @Test
    public void builder_WHEN_NullPricePerKg_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() ->
                WeightBasedProduct.builder().weightInKg("2").code("P1").build()
        );
    }

    @Test
    public void builder_WHEN_NotValidPricePerKg_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() ->
                WeightBasedProduct.builder().pricePerKg("a34").weightInKg("2").code("P1").build()
        );
    }

    @Test
    public void builder_WHEN_NegativePricePerKg_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() ->
                WeightBasedProduct.builder().pricePerKg("-34").weightInKg("2").code("P1").build()
        );
    }

    @Test
    public void builder_WHEN_NullWeightPerKg_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() ->
                WeightBasedProduct.builder().pricePerKg("2.0").code("P1").build()
        );
    }

    @Test
    public void builder_WHEN_NotValidWeightPerKg_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() ->
                WeightBasedProduct.builder().weightInKg("10c").pricePerKg("2.0").code("P1").build()
        );
    }

    @Test
    public void builder_WHEN_NegativeWeightPerKg_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() ->
                WeightBasedProduct.builder().weightInKg("-10").pricePerKg("2.0").code("P1").build()
        );
    }


    @Test
    public void builder_WHEN_AllValid_THEN_ShouldReturnCorrectProduct() {
        final WeightBasedProduct p1 = WeightBasedProduct.builder().weightInKg("10").pricePerKg("2.0").code("P1").build();
        Assertions.assertThat(p1).isNotNull();
        Assertions.assertThat(p1.getPrice()).isEqualByComparingTo("20.0");
        Assertions.assertThat(p1.getCode()).isEqualTo("P1");
    }
}
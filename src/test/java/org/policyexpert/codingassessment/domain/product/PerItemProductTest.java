package org.policyexpert.codingassessment.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PerItemProductTest {

    @Test
    public void builder_WHEN_NullCode_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() ->
                PerItemProduct.builder().price("2.0").build()
        );
    }

    @Test
    public void builder_WHEN_EmptyCode_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() ->
                PerItemProduct.builder().price("2.0").code("").build()
        );
    }

    @Test
    public void builder_WHEN_NullPrice_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() ->
                PerItemProduct.builder().code("P1").build()
        );
    }

    @Test
    public void builder_WHEN_NotANumberPrice_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() ->
                PerItemProduct.builder().price("a2.0").code("P1").build()
        );
    }

    @Test
    public void builder_WHEN_InvalidPrice_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() ->
                PerItemProduct.builder().price("-2.0").code("P1").build()
        );
    }

    @Test
    public void builder_WHEN_AllValid_THEN_ShouldReturnCorrectProduct() {
        final PerItemProduct p1 = PerItemProduct.builder().price("2.0").code("P1").build();
        Assertions.assertThat(p1).isNotNull();
        Assertions.assertThat(p1.getPrice()).isEqualByComparingTo("2.0");
        Assertions.assertThat(p1.getCode()).isEqualTo("P1");
    }
}
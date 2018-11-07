package org.policyexpert.codingassessment.domain.promotion;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.saving.Saving;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.policyexpert.codingassessment.domain.promotion.MockProduct.mockProduct;

public class FixedPricePromotionTest {


    @Test
    public void builder_WHEN_NullProduct_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(
                () ->
                        FixedPricePromotion.builder()
                                .fixedPrice("2.0")
                                .triggeringAmount(12)
                                .build()
        );
    }

    @Test
    public void builder_WHEN_NullFixedPrice_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(
                () ->
                        FixedPricePromotion.builder()
                                .product(mockProduct("P1", "2.50"))
                                .triggeringAmount(12)
                                .build()
        );
    }

    @Test
    public void builder_WHEN_NotANumberFixedPrice_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(
                () ->
                        FixedPricePromotion.builder()
                                .product(mockProduct("P1", "2.50"))
                                .triggeringAmount(12)
                                .fixedPrice("ss")
                                .build()
        );
    }

    @Test
    public void builder_WHEN_NegativeFixedPrice_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(
                () ->
                        FixedPricePromotion.builder()
                                .product(mockProduct("P1", "2.50"))
                                .triggeringAmount(12)
                                .fixedPrice("-3.5")
                                .build()
        );
    }

    @Test
    public void builder_WHEN_NoTriggerringAmount_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(
                () ->
                        FixedPricePromotion.builder()
                                .product(mockProduct("P1", "2.50"))
                                .fixedPrice("3.5")
                                .build()
        );
    }

    @Test
    public void builder_WHEN_NothingPresent_THEN_ShouldThrowIllegalArgumentException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> FixedPricePromotion.builder().build());
    }

    @Test
    public void builder_WHEN_AllGood_THEN_ShouldReturnPromotion() {
        final FixedPricePromotion promotion = FixedPricePromotion.builder()
                .product(mockProduct("P1", "2.50"))
                .fixedPrice("3.5")
                .triggeringAmount(2)
                .build();

        Assertions.assertThat(promotion.getId()).isNotEmpty();
    }

    @Test
    public void applyTo_WHEN_NullProducts_THEN_ShouldReturnNoSavings() {
        final FixedPricePromotion promotion =
                FixedPricePromotion.builder()
                        .triggeringAmount(2)
                        .product(mockProduct("P1", "2.50"))
                        .fixedPrice("2.0").build();
        final List<Saving> savings = promotion.applyTo(null);
        Assertions.assertThat(savings).isEmpty();
    }

    @Test
    public void applyTo_WHEN_EmptyProducts_THEN_ShouldReturnNoSavings() {
        final FixedPricePromotion promotion =
                FixedPricePromotion.builder()
                        .triggeringAmount(2)
                        .product(mockProduct("P1", "2.50"))
                        .fixedPrice("2.0").build();
        final List<Saving> savings = promotion.applyTo(Collections.emptyList());
        Assertions.assertThat(savings).isEmpty();
    }

    @Test
    public void applyTo_WHEN_NoMatchingProducts_THEN_ShouldReturnNoSavings() {
        final FixedPricePromotion promotion =
                FixedPricePromotion.builder()
                        .triggeringAmount(2)
                        .product(mockProduct("P1", "2.50"))
                        .fixedPrice("2.0").build();
        final Product p2 = mockProduct("P2", "52");
        final List<Saving> savings = promotion.applyTo(Arrays.asList(p2, p2));
        Assertions.assertThat(savings).isEmpty();
    }

    @Test
    public void applyTo_WHEN_NotEnoughMatchingProducts_THEN_ShouldReturnNoSavings() {
        final FixedPricePromotion promotion =
                FixedPricePromotion.builder()
                        .triggeringAmount(2)
                        .product(mockProduct("P1", "2.50"))
                        .fixedPrice("2.0").build();
        final Product p1 = mockProduct("P1", "2.50");
        final Product p2 = mockProduct("P2", "52");
        final List<Saving> savings = promotion.applyTo(Arrays.asList(p2, p2, p1));
        Assertions.assertThat(savings).isEmpty();
    }

    @Test
    public void applyTo_WHEN_EnoughMatchingProducts_THEN_ShouldReturnSavings() {
        final FixedPricePromotion promotion =
                FixedPricePromotion.builder()
                        .triggeringAmount(2)
                        .product(mockProduct("P1", "2.50"))
                        .fixedPrice("3.0").build();
        final Product p1 = mockProduct("P1", "3.00");
        final Product p2 = mockProduct("P2", "52");
        final List<Saving> savings = promotion.applyTo(Arrays.asList(p2, p2, p1, p1));
        Assertions.assertThat(savings).hasSize(1);
        Assertions.assertThat(savings.get(0).getName()).isEqualTo("P1 2 for £3.00");
        Assertions.assertThat(savings.get(0).getAmount()).isEqualByComparingTo("2.0");
    }

    @Test
    public void applyTo_WHEN_MoreMatchingProductsButNotEnoughForTwoTriggers_THEN_ShouldReturnOneSaving() {
        final FixedPricePromotion promotion =
                FixedPricePromotion.builder()
                        .triggeringAmount(2)
                        .product(mockProduct("P1", "2.50"))
                        .fixedPrice("3.0").build();
        final Product p1 = mockProduct("P1", "3.00");
        final Product p2 = mockProduct("P2", "52");
        final List<Saving> savings = promotion.applyTo(Arrays.asList(p2, p1, p2, p1, p1));
        Assertions.assertThat(savings).hasSize(1);
        Assertions.assertThat(savings.get(0).getName()).isEqualTo("P1 2 for £3.00");
        Assertions.assertThat(savings.get(0).getAmount()).isEqualByComparingTo("2.00");
    }

    @Test
    public void applyTo_WHEN_FourMatchingProductsBut_THEN_ShouldReturnTwoSavings() {
        final FixedPricePromotion promotion =
                FixedPricePromotion.builder()
                        .triggeringAmount(2)
                        .product(mockProduct("P1", "2.50"))
                        .fixedPrice("3.0").build();
        final Product p1 = mockProduct("P1", "3.00");
        final Product p2 = mockProduct("P2", "52");
        final List<Saving> savings = promotion.applyTo(Arrays.asList(p2, p1, p1, p2, p1, p1));
        Assertions.assertThat(savings).hasSize(2);
        Assertions.assertThat(savings.get(0).getName()).isEqualTo("P1 2 for £3.00");
        Assertions.assertThat(savings.get(0).getAmount()).isEqualByComparingTo("2.00");
        Assertions.assertThat(savings.get(1).getName()).isEqualTo("P1 2 for £3.00");
        Assertions.assertThat(savings.get(1).getAmount()).isEqualByComparingTo("2.00");
    }
}
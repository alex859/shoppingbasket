package org.policyexpert.codingassessment.domain.promotion;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.saving.Saving;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeForTwoPromotionTest {

    @Test
    public void applyTo_WHEN_NullProducts_THEN_NoSavings() {
        final ThreeForTwoPromotion promotion = new ThreeForTwoPromotion(MockProduct.mockProduct("P1", "2.25"));

        final List<Saving> saving = promotion.applyTo(null);
        Assertions.assertThat(saving).isEmpty();
    }

    @Test
    public void applyTo_WHEN_NoProducts_THEN_NoSavings() {
        final ThreeForTwoPromotion promotion = new ThreeForTwoPromotion(MockProduct.mockProduct("P1", "2.25"));

        final List<Saving> saving = promotion.applyTo(Collections.emptyList());
        Assertions.assertThat(saving).isEmpty();
    }

    @Test
    public void applyTo_WHEN_NoMatchingProducts_THEN_NoSavings() {
        final Product p2 = MockProduct.mockProduct("P2", "2.5");
        final ThreeForTwoPromotion promotion = new ThreeForTwoPromotion(MockProduct.mockProduct("P1", "2.25"));

        final List<Saving> saving = promotion.applyTo(Collections.singletonList(p2));
        Assertions.assertThat(saving).isEmpty();
    }

    @Test
    public void applyTo_WHEN_NotEnoughMatchingProducts_THEN_NoSavings() {
        final Product p1 = MockProduct.mockProduct("P1", "2.25");
        final Product p2 = MockProduct.mockProduct("P2", "2.5");
        final ThreeForTwoPromotion promotion = new ThreeForTwoPromotion(MockProduct.mockProduct("P1", "2.25"));

        final List<Saving> saving = promotion.applyTo(Arrays.asList(p1, p2, p2, p2, p1));
        Assertions.assertThat(saving).isEmpty();
    }

    @Test
    public void applyTo_WHEN_EnoughMatchingProducts_THEN_OneSaving() {
        final Product p1 = MockProduct.mockProduct("P1", "2.25");
        final Product p2 = MockProduct.mockProduct("P2", "2.5");
        final ThreeForTwoPromotion promotion = new ThreeForTwoPromotion(MockProduct.mockProduct("P1", "2.25"));

        final List<Saving> saving = promotion.applyTo(Arrays.asList(p1, p2, p1, p1));
        Assertions.assertThat(saving).hasSize(1);
        Assertions.assertThat(saving.get(0).getAmount()).isEqualByComparingTo("2.25");
        Assertions.assertThat(saving.get(0).getName()).isEqualTo("P1 3 for 2");
    }

    @Test
    public void applyTo_WHEN_MoreThanThreeMatchingButNotEnoughForSecondPromotionProducts_THEN_OneSaving() {
        final Product p1 = MockProduct.mockProduct("P1", "2.25");
        final Product p2 = MockProduct.mockProduct("P2", "2.5");
        final ThreeForTwoPromotion promotion = new ThreeForTwoPromotion(MockProduct.mockProduct("P1", "2.25"));

        final List<Saving> saving = promotion.applyTo(Arrays.asList(p1, p2, p1, p1, p1));
        Assertions.assertThat(saving).hasSize(1);
        Assertions.assertThat(saving.get(0).getAmount()).isEqualByComparingTo("2.25");
        Assertions.assertThat(saving.get(0).getName()).isEqualTo("P1 3 for 2");
    }

    @Test
    public void applyTo_WHEN_SixMatching_THEN_TwoSavings() {
        final Product p1 = MockProduct.mockProduct("P1", "2.25");
        final Product p2 = MockProduct.mockProduct("P2", "2.5");
        final ThreeForTwoPromotion promotion = new ThreeForTwoPromotion(MockProduct.mockProduct("P1", "2.25"));

        final List<Saving> saving = promotion.applyTo(Arrays.asList(p1, p2, p1, p1, p1, p2, p1, p1));
        Assertions.assertThat(saving).hasSize(2);
        Assertions.assertThat(saving.get(0).getAmount()).isEqualByComparingTo("2.25");
        Assertions.assertThat(saving.get(0).getName()).isEqualTo("P1 3 for 2");
        Assertions.assertThat(saving.get(1).getAmount()).isEqualByComparingTo("2.25");
        Assertions.assertThat(saving.get(1).getName()).isEqualTo("P1 3 for 2");
    }

    private static class MockProduct extends Product {
        private final BigDecimal price;

        private MockProduct(final String code, final BigDecimal price) {
            super(code);
            this.price = price;
        }

        private static Product mockProduct(final String code, final String price) {
            return new MockProduct(code, new BigDecimal(price));
        }

        @Override
        public BigDecimal getPrice() {
            return price;
        }
    }
}
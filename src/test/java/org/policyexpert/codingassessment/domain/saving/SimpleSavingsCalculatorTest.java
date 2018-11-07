package org.policyexpert.codingassessment.domain.saving;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.promotion.Promotion;
import org.policyexpert.codingassessment.domain.saving.Saving;
import org.policyexpert.codingassessment.domain.saving.SimpleSavingsCalculator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SimpleSavingsCalculatorTest {

    @Test
    public void calculate_WHEN_NullProducts_THEN_ResultWillHaveNoProductsAndNoSavings() {
        final Promotion promotion1 = Mockito.mock(Promotion.class);
        final Saving s1 = Mockito.mock(Saving.class);
        Mockito.when(promotion1.applyTo(ArgumentMatchers.anyList())).thenReturn(Optional.of(s1));
        final SimpleSavingsCalculator receiptCalculator = new SimpleSavingsCalculator();
        final List<Saving> savings = receiptCalculator.calculate(null, Collections.singletonList(promotion1));

        Assertions.assertThat(savings).isNotNull();
        Assertions.assertThat(savings).isEmpty();
    }

    @Test
    public void calculate_WHEN_NoProducts_THEN_ResultWillHaveNoProductsAndNoSavings() {
        final Promotion promotion1 = Mockito.mock(Promotion.class);
        final Saving s1 = Mockito.mock(Saving.class);
        Mockito.when(promotion1.applyTo(ArgumentMatchers.anyList())).thenReturn(Optional.of(s1));
        final SimpleSavingsCalculator receiptCalculator = new SimpleSavingsCalculator();
        final List<Saving> savings = receiptCalculator.calculate(Collections.emptyList(), Collections.singletonList(promotion1));

        Assertions.assertThat(savings).isNotNull();
        Assertions.assertThat(savings).isEmpty();
    }

    @Test
    public void calculate_WHEN_NullPromotions_THEN_ResultWillHaveNoSavings() {
        final SimpleSavingsCalculator receiptCalculator = new SimpleSavingsCalculator();
        final Product p1 = Mockito.mock(Product.class);
        final Product p2 = Mockito.mock(Product.class);
        final Product p3 = Mockito.mock(Product.class);
        final List<Saving> savings = receiptCalculator.calculate(Arrays.asList(p1, p2, p3), null);

        Assertions.assertThat(savings).isNotNull();
        Assertions.assertThat(savings).isEmpty();
    }

    @Test
    public void calculate_WHEN_NoPromotions_THEN_ResultWillHaveNoSavings() {
        final SimpleSavingsCalculator receiptCalculator = new SimpleSavingsCalculator();
        final Product p1 = Mockito.mock(Product.class);
        final Product p2 = Mockito.mock(Product.class);
        final Product p3 = Mockito.mock(Product.class);
        final List<Saving> savings = receiptCalculator.calculate(Arrays.asList(p1, p2, p3), Collections.emptyList());

        Assertions.assertThat(savings).isNotNull();
        Assertions.assertThat(savings).isEmpty();
    }

    @Test
    public void calculate_WHEN_SomePromotions_THEN_ResultWillHavePromotionSavings() {
        final Product p1 = Mockito.mock(Product.class);
        final Product p2 = Mockito.mock(Product.class);
        final Product p3 = Mockito.mock(Product.class);
        final List<Product> products = Arrays.asList(p1, p2, p3);
        final Saving s1 = Mockito.mock(Saving.class);
        final Saving s2 = Mockito.mock(Saving.class);
        final Promotion promotion1 = Mockito.mock(Promotion.class);
        Mockito.when(promotion1.applyTo(products)).thenReturn(Optional.of(s1));
        final Promotion promotion2 = Mockito.mock(Promotion.class);
        Mockito.when(promotion2.applyTo(products)).thenReturn(Optional.of(s2));
        final SimpleSavingsCalculator receiptCalculator = new SimpleSavingsCalculator();
        final List<Saving> savings = receiptCalculator.calculate(products, Arrays.asList(promotion1, promotion2));

        Assertions.assertThat(savings).isNotNull();
        Assertions.assertThat(savings).containsExactlyInAnyOrder(s1, s2);
    }

    @Test
    public void calculate_WHEN_SomePromotionsSomeWithNoSavings_THEN_ResultWillHavePromotionWithSavings() {
        final Product p1 = Mockito.mock(Product.class);
        final Product p2 = Mockito.mock(Product.class);
        final Product p3 = Mockito.mock(Product.class);
        final List<Product> products = Arrays.asList(p1, p2, p3);
        final Saving s1 = Mockito.mock(Saving.class);
        final Promotion promotion1 = Mockito.mock(Promotion.class);
        Mockito.when(promotion1.applyTo(products)).thenReturn(Optional.of(s1));
        final Promotion promotion2 = Mockito.mock(Promotion.class);
        Mockito.when(promotion2.applyTo(products)).thenReturn(Optional.empty());
        final SimpleSavingsCalculator receiptCalculator = new SimpleSavingsCalculator();
        final List<Saving> savings = receiptCalculator.calculate(products, Arrays.asList(promotion1, promotion2));

        Assertions.assertThat(savings).isNotNull();
        Assertions.assertThat(savings).containsExactlyInAnyOrder(s1);
    }
}
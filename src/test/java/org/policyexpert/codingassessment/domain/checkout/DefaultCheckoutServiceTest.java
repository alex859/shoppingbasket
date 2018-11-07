package org.policyexpert.codingassessment.domain.checkout;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.promotion.Promotion;
import org.policyexpert.codingassessment.domain.receipt.Receipt;
import org.policyexpert.codingassessment.domain.saving.Saving;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultCheckoutServiceTest {

    @Test
    public void generateReceiptFor_WHEN_NullProducts_THEN_NoSavings() {
        final DefaultCheckoutService checkoutService = DefaultCheckoutService.noPromotionsCheckoutService();
        final Receipt receipt = checkoutService.generateReceiptFor(null);

        Assertions.assertThat(receipt).isNotNull();
        Assertions.assertThat(receipt.getProducts()).isEmpty();
        Assertions.assertThat(receipt.getSavings()).isEmpty();
    }

    @Test
    public void generateReceiptFor_WHEN_NoProducts_THEN_NoSavings() {
        final DefaultCheckoutService checkoutService = DefaultCheckoutService.noPromotionsCheckoutService();
        final Receipt receipt = checkoutService.generateReceiptFor(Collections.emptyList());

        Assertions.assertThat(receipt).isNotNull();
        Assertions.assertThat(receipt.getProducts()).isEmpty();
        Assertions.assertThat(receipt.getSavings()).isEmpty();
    }

    @Test
    public void generateReceiptFor_WHEN_NoPromotions_THEN_NoSavings() {
        final DefaultCheckoutService checkoutService = DefaultCheckoutService.noPromotionsCheckoutService();
        final Product p1 = Mockito.mock(Product.class);
        final Product p2 = Mockito.mock(Product.class);
        final Product p3 = Mockito.mock(Product.class);
        final Receipt receipt = checkoutService.generateReceiptFor(Arrays.asList(p1, p2, p3));

        Assertions.assertThat(receipt).isNotNull();
        Assertions.assertThat(receipt.getProducts()).containsExactlyInAnyOrder(p1, p2, p3);
        Assertions.assertThat(receipt.getSavings()).isEmpty();

    }

    @Test
    public void generateReceiptFor_WHEN_SomePromotions_THEN_AppliedPromotionsInReceipt() {
        final Supplier<List<Promotion>> promotionSupplier = Mockito.mock(Supplier.class);
        final Product p1 = Mockito.mock(Product.class);
        final Product p2 = Mockito.mock(Product.class);
        final Product p3 = Mockito.mock(Product.class);

        final Promotion promotion1 = Mockito.mock(Promotion.class);
        final Saving saving1 = mock(Saving.class);
        final Saving saving2 = mock(Saving.class);
        when(promotion1.applyTo(Arrays.asList(p1, p2, p3))).thenReturn(Arrays.asList(saving1, saving2));

        final Saving saving3 = mock(Saving.class);
        final Promotion promotion2 = Mockito.mock(Promotion.class);
        when(promotion2.applyTo(Arrays.asList(p1, p2, p3))).thenReturn(Collections.singletonList(saving3));

        when(promotionSupplier.get()).thenReturn(Arrays.asList(promotion1, promotion2));

        final DefaultCheckoutService checkoutService = new DefaultCheckoutService(promotionSupplier);

        final Receipt receipt = checkoutService.generateReceiptFor(Arrays.asList(p1, p2, p3));

        Assertions.assertThat(receipt).isNotNull();
        Assertions.assertThat(receipt.getProducts()).containsExactlyInAnyOrder(p1, p2, p3);
        Assertions.assertThat(receipt.getSavings()).hasSize(3);
        Assertions.assertThat(receipt.getSavings()).containsExactlyInAnyOrder(saving1, saving2, saving3);
    }
}
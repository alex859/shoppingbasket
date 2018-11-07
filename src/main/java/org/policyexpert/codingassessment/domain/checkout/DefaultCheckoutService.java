package org.policyexpert.codingassessment.domain.checkout;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.promotion.Promotion;
import org.policyexpert.codingassessment.domain.receipt.Receipt;
import org.policyexpert.codingassessment.domain.saving.Saving;
import org.policyexpert.codingassessment.utils.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * DefaultCheckoutService generates a receipt given the promotions it knows about.
 */
public class DefaultCheckoutService implements CheckoutService {
    private final Supplier<List<Promotion>> promotionsSupplier;

    public DefaultCheckoutService(final Supplier<List<Promotion>> promotionsSupplier) {
        this.promotionsSupplier = promotionsSupplier == null ? Collections::emptyList : promotionsSupplier;
    }

    @Override
    public Receipt generateReceiptFor(final List<Product> products) {
        if (CollectionUtils.isEmpty(products)) {
            return Receipt.builder().build();
        }

        final List<Promotion> promotions = promotionsSupplier.get();
        final List<Saving> savings = promotions.stream()
                .map(promotion -> promotion.applyTo(products))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return Receipt.builder()
                .products(products)
                .savings(savings)
                .build();
    }

    public static DefaultCheckoutService noPromotionsCheckoutService() {
        return new DefaultCheckoutService(null);
    }
}

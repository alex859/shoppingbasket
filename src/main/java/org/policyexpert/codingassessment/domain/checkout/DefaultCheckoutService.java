package org.policyexpert.codingassessment.domain.checkout;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.promotion.Promotion;
import org.policyexpert.codingassessment.domain.receipt.Receipt;
import org.policyexpert.codingassessment.utils.ValidationUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * DefaultCheckoutService genearetes a receipt given the promotions it knows about.
 */
public class DefaultCheckoutService implements CheckoutService {
    private final Supplier<List<Promotion>> promotionsSupplier;

    public DefaultCheckoutService(final Supplier<List<Promotion>> promotionsSupplier) {
        this.promotionsSupplier = promotionsSupplier == null ? Collections::emptyList : promotionsSupplier;
    }

    @Override
    public Receipt generateReceiptFor(final List<Product> products) {
        return null;
    }
}

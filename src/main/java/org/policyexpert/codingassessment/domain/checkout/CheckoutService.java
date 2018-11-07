package org.policyexpert.codingassessment.domain.checkout;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.receipt.Receipt;

import java.util.List;

/**
 * Defines the logic of generating the receipt for a list of products at checkout.
 */
public interface CheckoutService {
    /**
     * @param products The products at checkout.
     * @return The generated receipt.
     */
    Receipt generateReceiptFor(List<Product> products);
}

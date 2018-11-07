package org.policyexpert.codingassessment.domain.promotion;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.saving.Saving;

import java.util.List;
import java.util.Optional;

/**
 * A promotion can be applied to a list of products to give customer a discount.
 */
public interface Promotion {
    Optional<Saving> applyTo(List<Product> products);
}

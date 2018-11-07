package org.policyexpert.codingassessment.domain.saving;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.promotion.Promotion;

import java.util.List;

/**
 * SavingsCalculator defines the action of calculating the {@link Saving}s for a given basket of {@link Product}s and {@link Promotion}s.
 * Implementations can do this with different level of complexity.
 * All {@link Promotion}s can be applied to all products, ore there can be rules with different complexity (e.g.: only one promotion
 * can be applied to a product, promotions have to be applied in a specific order...).
 */
public interface SavingsCalculator {

    /**
     * @param products   The products in the basket.
     * @param promotions The currently available promotions.
     * @return The savings for the given shopping basket.
     */
    List<Saving> calculate(List<Product> products, List<Promotion> promotions);
}

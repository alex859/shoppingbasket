package org.policyexpert.codingassessment.domain.promotion;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.saving.Saving;

import java.util.List;

/**
 * When you buy three items of the same type, you only pay 2.
 */
public class ThreeForTwoPromotion extends Promotion {
    private final Product product;

    /**
     * @param product The product subject to this promotion.
     */
    public ThreeForTwoPromotion(Product product) {
        this.product = product;
    }

    @Override
    public List<Saving> applyTo(final List<Product> products) {
        return null;
    }
}

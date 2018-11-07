package org.policyexpert.codingassessment.domain.promotion;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.saving.Saving;

import java.util.List;
import java.util.Optional;

/**
 * When you buy three items of the same type, you only pay 2.
 */
public class ThreeForTwoPromotion implements Promotion {

    @Override
    public Optional<Saving> applyTo(List<Product> products) {
        return null;
    }
}

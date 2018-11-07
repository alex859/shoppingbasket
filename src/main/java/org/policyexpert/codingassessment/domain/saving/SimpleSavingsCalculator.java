package org.policyexpert.codingassessment.domain.saving;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.promotion.Promotion;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SimpleSavingsCalculator simply applies all {@link Promotion}s to all {@link Product}s.
 */
public class SimpleSavingsCalculator implements SavingsCalculator{

    @Override
    public List<Saving> calculate(final List<Product> products, final List<Promotion> promotions) {
        if (isEmpty(products) || isEmpty(promotions)) {
            return Collections.emptyList();
        }

        return promotions.stream()
                .map(promotion -> promotion.applyTo(products))
                .filter(Optional::isPresent).map(Optional::get)
                .collect(Collectors.toList());
    }

    // Lazy to include CollectionUtils
    private <T> boolean isEmpty(final List<T> list) {
        return list == null || list.isEmpty();
    }
}

package org.policyexpert.codingassessment.domain.saving;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.promotion.Promotion;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.policyexpert.codingassessment.utils.CollectionUtils.isEmpty;

/**
 * SimpleSavingsCalculator simply applies all {@link Promotion}s to all {@link Product}s.
 */
public class SimpleSavingsCalculator implements SavingsCalculator {

    @Override
    public List<Saving> calculate(final List<Product> products, final List<Promotion> promotions) {
        if (isEmpty(products) || isEmpty(promotions)) {
            return Collections.emptyList();
        }

        return promotions.stream()
                .map(promotion -> promotion.applyTo(products))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}

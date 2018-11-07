package org.policyexpert.codingassessment.domain.promotion;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.saving.Saving;
import org.policyexpert.codingassessment.utils.CollectionUtils;
import org.policyexpert.codingassessment.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * When you buy three items of the same type, you only pay 2.
 */
public class ThreeForTwoPromotion extends Promotion {
    private static final String PROMO_SUFFIX = "3 for 2";
    private final Product product;
    private final Saving saving;

    /**
     * @param product The product subject to this promotion.
     */
    public ThreeForTwoPromotion(final Product product) {
        this.product = ValidationUtils.notNull(product, "Product");
        this.saving = createSaving(product);
    }

    @Override
    public List<Saving> applyTo(final List<Product> products) {
        if (CollectionUtils.isEmpty(products)) {
            return Collections.emptyList();
        }

        final long matchingProducts = products.stream()
                .filter(this.product::equals)
                .count();

        final long promotionsTriggering = matchingProducts / 3;
        final List<Saving> result = new LinkedList<>();
        for (int i = 0; i < promotionsTriggering; i++) {
            result.add(this.saving);
        }

        return result;
    }

    private Saving createSaving(final Product product) {
        return Saving.builder()
                .name(String.format("%s %s", product.getCode(), PROMO_SUFFIX))
                .amount(product.getPrice()).build();
    }
}

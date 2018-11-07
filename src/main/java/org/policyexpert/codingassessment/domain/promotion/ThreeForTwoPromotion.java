package org.policyexpert.codingassessment.domain.promotion;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.saving.Saving;
import org.policyexpert.codingassessment.utils.CollectionUtils;
import org.policyexpert.codingassessment.utils.ValidationUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * When you buy three items of the same type, you only pay 2.
 */
public class ThreeForTwoPromotion extends Promotion {
    private static final String PROMO_PATTERN = "%s 3 for 2";
    private final Product product;

    private ThreeForTwoPromotion(final Builder builder) {
        this.product = builder.product;
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
        final Saving saving = createSaving(this.product);
        for (int i = 0; i < promotionsTriggering; i++) {
            result.add(saving);
        }

        return result;
    }

    private static Saving createSaving(final Product product) {
        ValidationUtils.notNull(product, "Product");
        return Saving.builder()
                .name(String.format(PROMO_PATTERN, product.getCode()))
                .amount(product.getPrice()).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Product product;

        public Builder product(final Product product) {
            this.product = ValidationUtils.notNull(product, "Product");
            return this;
        }

        public ThreeForTwoPromotion build() {
            final ThreeForTwoPromotion threeForTwoPromotion = new ThreeForTwoPromotion(this);
            ValidationUtils.notNull(threeForTwoPromotion.product, "Product");

            return threeForTwoPromotion;
        }
    }
}

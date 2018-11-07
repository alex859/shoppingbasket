package org.policyexpert.codingassessment.domain.promotion;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.saving.Saving;
import org.policyexpert.codingassessment.utils.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.policyexpert.codingassessment.utils.ValidationUtils.isPositive;
import static org.policyexpert.codingassessment.utils.ValidationUtils.isPositiveOrZero;
import static org.policyexpert.codingassessment.utils.ValidationUtils.notNull;
import static org.policyexpert.codingassessment.utils.ValidationUtils.readBigDecimal;

/**
 * FixedPricePromotion lets you pay a fixed amount when you buy N same products.
 */
public class FixedPricePromotion extends Promotion {
    private static final String PROMO_PATTERN = "%s %d for £%.2f";
    private final Integer triggeringAmount;
    private final Product product;
    private final BigDecimal fixedPrice;

    private FixedPricePromotion(final Builder builder) {
        this.triggeringAmount = builder.triggeringAmount;
        this.product = builder.product;
        this.fixedPrice = builder.fixedPrice;
    }

    private static Saving createSaving(final Integer triggeringAmount, final Product product, final BigDecimal fixedPrice) {
        final BigDecimal savingAmount =
                product.getPrice()
                        .multiply(BigDecimal.valueOf(triggeringAmount))
                        .subtract(fixedPrice);
        return Saving.builder()
                .name(String.format(PROMO_PATTERN, product.getCode(), triggeringAmount, fixedPrice))
                .amount(savingAmount).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public List<Saving> applyTo(final List<Product> products) {
        if (CollectionUtils.isEmpty(products)) {
            return Collections.emptyList();
        }

        final long matchingProducts = products.stream()
                .filter(this.product::equals)
                .count();

        final long promotionsTriggering = matchingProducts / triggeringAmount;
        final List<Saving> result = new LinkedList<>();
        final Saving saving = createSaving(this.triggeringAmount, this.product, this.fixedPrice);
        for (int i = 0; i < promotionsTriggering; i++) {
            result.add(saving);
        }

        return result;
    }

    public static class Builder {
        private Integer triggeringAmount;
        private Product product;
        private BigDecimal fixedPrice;

        public Builder triggeringAmount(final Integer triggeringAmount) {
            this.triggeringAmount = triggeringAmount;
            return this;
        }

        public Builder product(final Product product) {
            this.product = product;
            return this;
        }

        public Builder fixedPrice(final String fixedPrice) {
            this.fixedPrice = readBigDecimal(fixedPrice);
            return this;
        }

        public FixedPricePromotion build() {
            final FixedPricePromotion fixedPricePromotion = new FixedPricePromotion(this);
            notNull(fixedPricePromotion.product, "Product");
            isPositiveOrZero(fixedPricePromotion.fixedPrice, "Fixed price");
            isPositive(fixedPricePromotion.triggeringAmount, "Triggering amount");

            return fixedPricePromotion;
        }


    }
}

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

/**
 * FixedPricePromotion lets you pay a fixed amount when you buy N same products.
 */
public class FixedPricePromotion extends Promotion {
    private static final String PROMO_PATTERN = "%s %d for £%.2f";
    private final int triggeringAmount;
    private final Product product;
    private final Saving saving;

    public FixedPricePromotion(final Integer triggeringAmount, final Product product, final BigDecimal fixedPrice) {
        this.triggeringAmount = isPositive(triggeringAmount, "Triggering amount");
        this.product = notNull(product, "Product");
        this.saving = createSaving(triggeringAmount, product, isPositiveOrZero(fixedPrice, "Fixed price"));
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
        for (int i = 0; i < promotionsTriggering; i++) {
            result.add(this.saving);
        }

        return result;
    }

    private Saving createSaving(final int triggeringAmount, final Product product, BigDecimal fixedPrice) {
        final BigDecimal savingAmount =
                product.getPrice()
                        .multiply(BigDecimal.valueOf(triggeringAmount))
                        .subtract(fixedPrice);
        return Saving.builder()
                .name(String.format(PROMO_PATTERN, product.getCode(), triggeringAmount, fixedPrice))
                .amount(savingAmount).build();
    }
}

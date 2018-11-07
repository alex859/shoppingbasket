package org.policyexpert.codingassessment.domain.promotion;


import org.policyexpert.codingassessment.domain.product.Product;

import java.math.BigDecimal;

/**
 * {@link Product} to be used in tests when we want to make sure the actual {@link Product#equals(Object)} method is called.
 *
 */
class MockProduct extends Product {
    private final BigDecimal price;

    private MockProduct(final String code, final BigDecimal price) {
        super(code);
        this.price = price;
    }

    static Product mockProduct(final String code, final String price) {
        return new MockProduct(code, new BigDecimal(price));
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}

package org.policyexpert.codingassessment.domain.product;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Simple abstraction of a Product in our shopping list.
 */
public abstract class Product {
    private final String code;

    protected Product(String code) {
        this.code = code;
    }

    /**
     * @return Product code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Product price.
     */
    public abstract BigDecimal getPrice();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(code, product.code);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code);
    }
}

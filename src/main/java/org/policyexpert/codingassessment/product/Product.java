package org.policyexpert.codingassessment.product;

import java.math.BigDecimal;
import java.util.Objects;

import static org.policyexpert.codingassessment.utils.ValidationUtils.notNull;

abstract class Product {
    protected final String code;

    protected Product(final String code) {
        this.code = notNull(code, "Product code");
    }

    public abstract BigDecimal getPrice();

    public String getCode() {
        return code;
    }

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

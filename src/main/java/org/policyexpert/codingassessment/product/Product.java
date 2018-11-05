package org.policyexpert.codingassessment.product;

import java.math.BigDecimal;

import static org.policyexpert.codingassessment.utils.ValidationUtils.notNull;

abstract class Product {
    private final String code;

    protected Product(final String code) {
        this.code = notNull(code, "Product code");
    }

    public abstract BigDecimal getPrice();

    public String getCode() {
        return code;
    }
}

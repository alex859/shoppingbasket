package org.policyexpert.codingassessment.domain;

import java.math.BigDecimal;
import java.util.Objects;

import static org.policyexpert.codingassessment.utils.ValidationUtils.isPositiveOrZero;
import static org.policyexpert.codingassessment.utils.ValidationUtils.notEmpty;
import static org.policyexpert.codingassessment.utils.ValidationUtils.notNull;
import static org.policyexpert.codingassessment.utils.ValidationUtils.readBigDecimal;

public class WeightBasedProduct implements Product {

    private final String code;
    private final BigDecimal pricePerKg;
    private final BigDecimal weightInKg;

    private WeightBasedProduct(final Builder builder) {
        this.code = builder.code;
        this.pricePerKg = builder.pricePerKg;
        this.weightInKg = builder.weightInKg;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public BigDecimal getPrice() {
        return this.weightInKg.multiply(this.pricePerKg);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WeightBasedProduct{");
        sb.append("code='").append(code).append('\'');
        sb.append(", pricePerKg=").append(pricePerKg);
        sb.append(", weightInKg=").append(weightInKg);
        sb.append(", price=").append(getPrice());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object ob) {
        if (this == ob) return true;
        if (ob == null || getClass() != ob.getClass()) return false;
        WeightBasedProduct product = (WeightBasedProduct) ob;
        return Objects.equals(code, product.code);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code);
    }

    public static class Builder {
        private String code;
        private BigDecimal pricePerKg;
        private BigDecimal weightInKg;

        public Builder code(final String code) {
            this.code = notEmpty(code, "Product code");
            return this;
        }

        public Builder pricePerKg(final String pricePerKg) {
            this.pricePerKg = isPositiveOrZero(readBigDecimal(pricePerKg), "Product price per kg");
            return this;
        }

        public Builder weightInKg(final String weightInKg) {
            this.weightInKg = isPositiveOrZero(readBigDecimal(weightInKg), "Product weight in kg");
            return this;
        }

        public WeightBasedProduct build() {
            final WeightBasedProduct product = new WeightBasedProduct(this);
            notNull(product.code, "Product code");
            notNull(product.pricePerKg, "Product price per kg");
            notNull(product.weightInKg, "Product weight in kg");

            return product;
        }
    }
}

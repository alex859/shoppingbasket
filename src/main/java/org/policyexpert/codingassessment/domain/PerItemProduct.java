package org.policyexpert.codingassessment.domain;

import java.math.BigDecimal;
import java.util.Objects;

import static org.policyexpert.codingassessment.utils.ValidationUtils.isPositiveOrZero;
import static org.policyexpert.codingassessment.utils.ValidationUtils.notEmpty;
import static org.policyexpert.codingassessment.utils.ValidationUtils.notNull;
import static org.policyexpert.codingassessment.utils.ValidationUtils.readBigDecimal;

public class PerItemProduct implements Product {

    private final String code;
    private final BigDecimal price;

    private PerItemProduct(final Builder builder) {
        this.code = builder.code;
        this.price = builder.price;
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
        return this.price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PerItemProduct{");
        sb.append("code='").append(code).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PerItemProduct that = (PerItemProduct) obj;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public static class Builder {
        private String code;
        private BigDecimal price;

        public Builder code(final String code) {
            this.code = notEmpty(code, "Product code");
            return this;
        }

        public Builder price(final String price) {
            this.price = isPositiveOrZero(readBigDecimal(price), "Product price");
            return this;
        }

        public PerItemProduct build() {
            final PerItemProduct product = new PerItemProduct(this);
            notNull(product.code, "Product code");
            notNull(product.price, "Product price");

            return product;
        }
    }
}

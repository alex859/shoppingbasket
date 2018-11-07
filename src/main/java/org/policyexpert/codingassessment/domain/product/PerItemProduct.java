package org.policyexpert.codingassessment.domain.product;

import java.math.BigDecimal;

import static org.policyexpert.codingassessment.utils.ValidationUtils.isPositiveOrZero;
import static org.policyexpert.codingassessment.utils.ValidationUtils.notEmpty;
import static org.policyexpert.codingassessment.utils.ValidationUtils.notNull;
import static org.policyexpert.codingassessment.utils.ValidationUtils.readBigDecimal;

public class PerItemProduct extends Product {

    private final BigDecimal price;

    private PerItemProduct(final Builder builder) {
        super(builder.code);
        this.price = builder.price;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PerItemProduct{");
        sb.append("code='").append(getCode()).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
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
            notNull(product.getCode(), "Product code");
            notNull(product.price, "Product price");

            return product;
        }
    }
}

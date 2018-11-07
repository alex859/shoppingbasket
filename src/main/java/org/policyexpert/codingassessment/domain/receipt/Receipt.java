package org.policyexpert.codingassessment.domain.receipt;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.saving.Saving;

import java.math.BigDecimal;
import java.util.*;

import static org.policyexpert.codingassessment.utils.ValidationUtils.notEmpty;

/**
 * Models a receipt containing products an savings.
 */
public class Receipt {
    private final String id;
    private final List<Product> products;
    private final List<Saving> savings;

    private Receipt(final Builder builder) {
        this.id = builder.id;
        this.products = builder.products;
        this.savings = builder.savings;
    }

    public String getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Saving> getSavings() {
        return savings;
    }

    public BigDecimal getSubTotal() {
        return this.products.stream().map(Product::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getTotalSavings() {
        return this.savings.stream().map(Saving::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getTotal() {
        return getSubTotal().subtract(getTotalSavings());
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Receipt that = (Receipt) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Receipt{");
        sb.append("id='").append(id).append('\'');
        sb.append(", products=").append(products);
        sb.append(", savings=").append(savings);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private String id = UUID.randomUUID().toString();
        private List<Product> products = new ArrayList<>();
        private List<Saving> savings = new ArrayList<>();

        public Builder id(final String id) {
            this.id = notEmpty(id, "Shopping basket ID");
            return this;
        }

        public Builder products(final List<Product> products) {
            this.products = products == null ? Collections.emptyList() : Collections.unmodifiableList(products);
            return this;
        }

        public Builder savings(final List<Saving> savings) {
            this.savings = savings == null ? Collections.emptyList(): Collections.unmodifiableList(savings);
            return this;
        }

        public Receipt build() {
            final Receipt receipt = new Receipt(this);
            if (receipt.products.isEmpty() && !receipt.savings.isEmpty()) {
                throw new IllegalStateException("Cannot have savings without products.");
            }
            return receipt;
        }
    }


}

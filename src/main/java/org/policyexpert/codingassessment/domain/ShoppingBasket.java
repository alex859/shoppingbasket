package org.policyexpert.codingassessment.domain;

import java.math.BigDecimal;
import java.util.*;

import static org.policyexpert.codingassessment.utils.ValidationUtils.notEmpty;

public class ShoppingBasket {
    private final String id;
    private final List<Product> products;
    private final List<Saving> savings;

    private ShoppingBasket(final Builder builder) {
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
        return null;
    }

    public BigDecimal getTotal() {
        return null;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ShoppingBasket that = (ShoppingBasket) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShoppingBasket{");
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

        public ShoppingBasket build() {
            return new ShoppingBasket(this);
        }
    }


}

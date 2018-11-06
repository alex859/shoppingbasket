package org.policyexpert.codingassessment.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.policyexpert.codingassessment.utils.ValidationUtils.notEmpty;
import static org.policyexpert.codingassessment.utils.ValidationUtils.notNull;

public class ShoppingBasket {
    private final String id;
    private final Map<Product, Long> products;

    private ShoppingBasket(final Builder builder) {
        this.id = builder.id;
        this.products = builder.products;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * @return A mapping product -> quantity.
     */
    public Map<Product, Long> getProducts() {
        return products;
    }

    public String getId() {
        return id;
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
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private String id = UUID.randomUUID().toString();
        private Map<Product, Long> products = new HashMap<>();

        public Builder id(final String id) {
            this.id = notEmpty(id, "Shopping basket ID");
            return this;
        }

        public Builder products(final Product... products) {
            this.products = products == null ? new HashMap<>() : createProductMap(products);
            return this;
        }

        private Map<Product, Long> createProductMap(Product[] products) {
            return Stream.of(products).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }

        public ShoppingBasket build() {
            final ShoppingBasket shoppingBasket = new ShoppingBasket(this);
            notNull(shoppingBasket.id, "Shopping basket ID");
            notNull(shoppingBasket.products, "Shopping basket products");
            return shoppingBasket;
        }
    }


}

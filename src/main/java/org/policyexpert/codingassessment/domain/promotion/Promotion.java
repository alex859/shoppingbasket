package org.policyexpert.codingassessment.domain.promotion;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.saving.Saving;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * A promotion can be applied to a list of products to give customer a discount.
 */
public abstract class Promotion {
    private final String id;

    protected Promotion() {
        this.id = UUID.randomUUID().toString();
    }

    protected Promotion(String id) {
        this.id = id;
    }

    /**
     * @return Promotion identifier.
     */
    public String getId() {
        return id;
    }

    /**
     * @param products The products to apply the promotion to.
     * @return How much the customer saves thanks to this promotion. If nothing is saven then returns {@link Optional#empty()}.
     */
    abstract Optional<Saving> applyTo(List<Product> products);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promotion promotion = (Promotion) o;
        return Objects.equals(id, promotion.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}

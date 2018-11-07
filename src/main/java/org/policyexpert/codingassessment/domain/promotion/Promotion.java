package org.policyexpert.codingassessment.domain.promotion;

import org.policyexpert.codingassessment.domain.product.Product;
import org.policyexpert.codingassessment.domain.saving.Saving;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * A promotion can be applied to a list of products to give customer a discount.
 */
public abstract class Promotion {
    private final String id;

    /**
     * When no identifier is provided, a random UUID is used to identify the promotion.
     */
    protected Promotion() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * @param id The promotion identifier.
     */
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
     * Works out how much a user saves thank to this promotion. If the promotion triggers more than once, more {@link Saving}s
     * are returned.
     *
     * @param products The products to apply the promotion to.
     * @return How much the customer saves thanks to this promotion. If the promotion does not trigger, an empty {@link List} i returned.
     */
    public abstract List<Saving> applyTo(List<Product> products);

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

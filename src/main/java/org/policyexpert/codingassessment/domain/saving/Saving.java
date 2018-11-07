package org.policyexpert.codingassessment.domain.saving;

import java.math.BigDecimal;
import java.util.Objects;

import static org.policyexpert.codingassessment.utils.ValidationUtils.isPositiveOrZero;
import static org.policyexpert.codingassessment.utils.ValidationUtils.notEmpty;
import static org.policyexpert.codingassessment.utils.ValidationUtils.notNull;

public class Saving {
    private final String name;
    private final BigDecimal amount;

    private Saving(final Builder builder) {
        this.name = builder.name;
        this.amount = builder.amount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Saving saving = (Saving) o;
        return Objects.equals(name, saving.name) &&
                Objects.equals(amount, saving.amount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, amount);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Saving{");
        sb.append("name='").append(name).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private String name;
        private BigDecimal amount;

        public Builder name(final String name) {
            this.name = notEmpty(name, "Saving name");
            return this;
        }

        public Builder amount(final BigDecimal amount) {
            this.amount = isPositiveOrZero(amount, "Saving amount");
            return this;
        }

        public Saving build() {
            final Saving saving = new Saving(this);
            notNull(saving.name, "Saving name");
            notNull(saving.amount, "Saving amount");

            return saving;
        }
    }

}

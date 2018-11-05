package org.policyexpert.codingassessment.product;

import org.policyexpert.codingassessment.utils.ValidationUtils;

import java.math.BigDecimal;

import static org.policyexpert.codingassessment.utils.ValidationUtils.notNull;

public class WeightBasedProduct extends Product {

    private final BigDecimal pricePerKg;
    private final BigDecimal weightInKg;

    public WeightBasedProduct(String code, String pricePerKg, String weightInKg) {
        super(code);
        this. pricePerKg = new BigDecimal(notNull(pricePerKg, "Price per kg"));
        this. weightInKg = new BigDecimal(notNull(weightInKg, "Weight in kg"));
    }

    @Override
    public BigDecimal getPrice() {
        return this.pricePerKg.multiply(weightInKg);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WeightBasedProduct{");
        sb.append("pricePerKg=").append(pricePerKg);
        sb.append(", weightInKg=").append(weightInKg);
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

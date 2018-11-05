package org.policyexpert.codingassessment.product;

import java.math.BigDecimal;

import static org.policyexpert.codingassessment.utils.ValidationUtils.isPositiveOrZero;
import static org.policyexpert.codingassessment.utils.ValidationUtils.readBigDecimal;

public class PerItemProduct  extends Product {

    private final BigDecimal itemPrice;

    public PerItemProduct(String code, String itemPrice) {
        super(code);
        this.itemPrice = isPositiveOrZero(readBigDecimal(itemPrice), "Item price");
    }

    @Override
    public BigDecimal getPrice() {
        return this.itemPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PerItemProduct{");
        sb.append("itemPrice=").append(itemPrice);
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

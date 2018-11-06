package org.policyexpert.codingassessment.product;

import java.math.BigDecimal;

/**
 * Simple abstraction of a Product in our shopping list.
 */
public interface Product {
    /**
     *
     * @return Product code.
     */
    String getCode();

    /**
     *
     * @return Product price.
     */
    BigDecimal getPrice();
}

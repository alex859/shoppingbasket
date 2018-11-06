package org.policyexpert.codingassessment.product;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.policyexpert.codingassessment.domain.Product;
import org.policyexpert.codingassessment.domain.Saving;
import org.policyexpert.codingassessment.domain.ShoppingBasket;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.Mockito.when;

public class ShoppingBasketTestTest {

    @Test
    public void getSubTotal_WHEN_EmptyBasket_THEN_ShouldReturnZero() {
        final ShoppingBasket shoppingBasket = ShoppingBasket.builder().build();

        Assertions.assertThat(shoppingBasket.getSubTotal()).isEqualByComparingTo("0.00");
    }

    @Test
    public void getSubTotal_WHEN_ProductsOnly_THEN_ShouldReturnSumOfProductPrices() {
        final ShoppingBasket shoppingBasket =
                ShoppingBasket.builder()
                        .products(
                                Arrays.asList(
                                        productPriced("1.25"),
                                        productPriced("2.5"),
                                        productPriced("8.2")
                                )
                        )
                        .build();

        Assertions.assertThat(shoppingBasket.getSubTotal()).isEqualByComparingTo("11.95");
    }

    @Test
    public void getSubTotal_WHEN_ProductsAndSavings_THEN_ShouldReturnSumOfProductPrices() {
        final ShoppingBasket shoppingBasket =
                ShoppingBasket.builder()
                        .products(Arrays.asList(
                                productPriced("1.25"),
                                productPriced("2.5"),
                                productPriced("8.2")
                        ))
                        .savings(Arrays.asList(
                                savingOf("5"),
                                savingOf("0.5")
                        ))
                        .build();

        Assertions.assertThat(shoppingBasket.getSubTotal()).isEqualByComparingTo("11.95");
    }

    @Test
    public void getTotal_WHEN_EmptyBasket_THEN_ShouldReturnZero() {
        final ShoppingBasket shoppingBasket = ShoppingBasket.builder().build();

        Assertions.assertThat(shoppingBasket.getTotal()).isEqualByComparingTo("0.00");
    }

    @Test
    public void getTotal_WHEN_ProductsOnly_THEN_ShouldReturnSumOfProductPrices() {
        final ShoppingBasket shoppingBasket =
                ShoppingBasket.builder()
                        .products(
                                Arrays.asList(
                                        productPriced("1.25"),
                                        productPriced("2.5"),
                                        productPriced("8.2")
                                )
                        )
                        .build();

        Assertions.assertThat(shoppingBasket.getTotal()).isEqualByComparingTo("11.95");
    }

    @Test
    public void getTotal_WHEN_ProductsAndSavings_THEN_ShouldReturnDifferenceOfProductsAndSavings() {
        final ShoppingBasket shoppingBasket =
                ShoppingBasket.builder()
                        .products(Arrays.asList(
                                productPriced("1.25"),
                                productPriced("2.5"),
                                productPriced("8.2")
                        ))
                        .savings(Arrays.asList(
                                savingOf("5"),
                                savingOf("0.5")
                        ))
                        .build();

        Assertions.assertThat(shoppingBasket.getSubTotal()).isEqualByComparingTo("6.45");
    }

    @Test
    public void getTotal_WHEN_SavingsAndNoProducts_THEN_ShouldThrowIllegalStateException() {
        Assertions.assertThatIllegalStateException().isThrownBy(
                () -> ShoppingBasket.builder()
                        .savings(Arrays.asList(
                                savingOf("5"),
                                savingOf("0.5")
                        ))
                        .build()
        );
    }

    private Product productPriced(final String price) {
        final Product product = Mockito.mock(Product.class);
        when(product.getPrice()).thenReturn(new BigDecimal(price));

        return product;
    }

    private Saving savingOf(final String amount) {
        final Saving saving = Mockito.mock(Saving.class);
        when(saving.getAmount()).thenReturn(new BigDecimal(amount));

        return saving;
    }
}
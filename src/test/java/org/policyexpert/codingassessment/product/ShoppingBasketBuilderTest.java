package org.policyexpert.codingassessment.product;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.policyexpert.codingassessment.domain.PerItemProduct;
import org.policyexpert.codingassessment.domain.ShoppingBasket;
import org.policyexpert.codingassessment.domain.WeightBasedProduct;

import java.util.AbstractMap;
import java.util.Map;

public class ShoppingBasketBuilderTest
{
	@Test
	public void getProducts_WHEN_ProductListNull_THEN_ShouldReturnEmptyMap()
	{
		final ShoppingBasket shoppingBasket = ShoppingBasket.builder().products(null).build();
		Assertions.assertThat(shoppingBasket.getProducts()).isEmpty();
	}

	@Test
	public void getProducts_WHEN_ProductListEmpty_THEN_ShouldReturnEmptyMap()
	{
		final ShoppingBasket shoppingBasket = ShoppingBasket.builder().products().build();
		Assertions.assertThat(shoppingBasket.getProducts()).isEmpty();
	}

	@Test
	public void getProducts_WHEN_ProductListNotEmpty_THEN_ShouldGroupProductsCorrectly()
	{
		final ShoppingBasket shoppingBasket = ShoppingBasket.builder().products(
				PerItemProduct.builder().code("Coke").price("1.2").build(),
				WeightBasedProduct.builder().code("Apple").pricePerKg("0.5").weightInKg("2").build(),
				PerItemProduct.builder().code("Coke").price("1.2").build(),
				PerItemProduct.builder().code("Beans").price("1.25").build()

		).build();
		Assertions.assertThat(shoppingBasket.getProducts()).hasSize(3);
		Assertions.assertThat(shoppingBasket.getProducts()).contains(
				new AbstractMap.SimpleEntry<>(PerItemProduct.builder().code("Coke").price("1.2").build(), 2L),
				new AbstractMap.SimpleEntry<>(PerItemProduct.builder().code("Beans").price("1.25").build(), 1L),
				new AbstractMap.SimpleEntry<>(WeightBasedProduct.builder().code("Apple").pricePerKg("0.5").weightInKg("2").build(), 1L)
		);
	}
}
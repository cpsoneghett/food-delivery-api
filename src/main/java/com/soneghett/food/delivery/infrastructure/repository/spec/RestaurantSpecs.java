package com.soneghett.food.delivery.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.soneghett.food.delivery.domain.model.Restaurant;

public class RestaurantSpecs {

	private RestaurantSpecs() {
	}

	public static Specification<Restaurant> withFreeDelivery() {
		return (root, query, builder) -> builder.equal(root.get("freightTax"), BigDecimal.ZERO);
	}

	public static Specification<Restaurant> withSimilarNames(String name) {
		return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
	}
}

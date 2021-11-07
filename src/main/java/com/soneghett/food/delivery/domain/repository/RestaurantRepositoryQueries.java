package com.soneghett.food.delivery.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.soneghett.food.delivery.domain.model.Restaurant;

public interface RestaurantRepositoryQueries {

	List<Restaurant> find(String name, BigDecimal initialFreightRate, BigDecimal finalFreightRate);
}
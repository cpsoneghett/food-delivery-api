package com.soneghett.food.delivery.domain.repository;

import java.util.List;

import com.soneghett.food.delivery.domain.model.Restaurant;

public interface RestaurantRepository {

	List<Restaurant> listAll();

	Restaurant findById(Long id);

	Restaurant save(Restaurant restaurant);

}

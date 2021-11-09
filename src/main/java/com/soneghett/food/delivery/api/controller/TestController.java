package com.soneghett.food.delivery.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soneghett.food.delivery.domain.model.Kitchen;
import com.soneghett.food.delivery.domain.model.Restaurant;
import com.soneghett.food.delivery.domain.repository.KitchenRepository;
import com.soneghett.food.delivery.domain.repository.RestaurantRepository;

@RestController
@RequestMapping("test")
public class TestController {

	@Autowired
	private KitchenRepository kitchenRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@GetMapping("/kitchens/by-name")
	public List<Kitchen> kitchensByName(String name) {
		return kitchenRepository.findAllByNameContaining(name);
	}

	@GetMapping("/restaurants/by-name-freight")
	public List<Restaurant> restaurantesPorNomeFrete(String name, BigDecimal initialFreightRate,
			BigDecimal finalFreightRate) {
		return restaurantRepository.find(name, initialFreightRate, finalFreightRate);
	}

	@GetMapping("/restaurants/by-name-freight2")
	public List<Restaurant> restaurantesPorNomeFrete2(String name, BigDecimal initialFreightRate,
			BigDecimal finalFreightRate) {
		return restaurantRepository.findWithCriteria(name, initialFreightRate, finalFreightRate);
	}

	@GetMapping("/restaurants/free-delivery")
	public List<Restaurant> getWithFreeDelivery(String name) {

		return restaurantRepository.findWithFreeDelivery(name);
	}
}

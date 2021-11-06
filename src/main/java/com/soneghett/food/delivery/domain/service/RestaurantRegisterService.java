package com.soneghett.food.delivery.domain.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soneghett.food.delivery.domain.model.Kitchen;
import com.soneghett.food.delivery.domain.model.Restaurant;
import com.soneghett.food.delivery.domain.repository.KitchenRepository;
import com.soneghett.food.delivery.domain.repository.RestaurantRepository;

@Service
public class RestaurantRegisterService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private KitchenRepository kitchenRepository;

	public Restaurant save(Restaurant restaurant) {

		Long kitchenId = restaurant.getKitchen().getId();

		validateKitchen(kitchenId);

		return restaurantRepository.save(restaurant);
	}

	public Restaurant update(Long idRestaurant, Restaurant newRestaurant) {

		try {
			var currentRestaurant = restaurantRepository.findById(idRestaurant);

			if (currentRestaurant.isEmpty())
				throw new EntityNotFoundException(String.format("Restaurant with id %d not found!!", idRestaurant));

			validateKitchen(newRestaurant.getKitchen().getId());

			BeanUtils.copyProperties(newRestaurant, currentRestaurant.get(), "id");

			return restaurantRepository.save(currentRestaurant.get());

		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(e.getMessage());
		}

	}

	private Kitchen validateKitchen(Long kitchenId) {

		return kitchenRepository.findById(kitchenId).orElseThrow(
				() -> new EntityNotFoundException(String.format("Kitchen with id %d not found!", kitchenId)));

	}
}

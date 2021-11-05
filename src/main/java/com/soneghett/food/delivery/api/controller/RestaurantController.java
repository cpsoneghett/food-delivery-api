package com.soneghett.food.delivery.api.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soneghett.food.delivery.domain.model.Restaurant;
import com.soneghett.food.delivery.domain.repository.RestaurantRepository;
import com.soneghett.food.delivery.domain.service.RestaurantRegisterService;

@RestController
@RequestMapping(value = "restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RestaurantRegisterService restaurantRegisterService;

	@GetMapping
	public List<Restaurant> listAll() {
		return restaurantRepository.listAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> findById(@PathVariable Long id) {

		var restaurant = restaurantRepository.findById(id);

		if (restaurant != null) {

			return ResponseEntity.ok(restaurant);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {

		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurantRegisterService.save(restaurant));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant newRestaurant) {

		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurantRegisterService.update(id, newRestaurant));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}

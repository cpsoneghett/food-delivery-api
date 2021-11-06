package com.soneghett.food.delivery.api.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soneghett.food.delivery.domain.exception.BindedEntityException;
import com.soneghett.food.delivery.domain.model.Kitchen;
import com.soneghett.food.delivery.domain.repository.KitchenRepository;
import com.soneghett.food.delivery.domain.service.KitchenRegisterService;

@RestController
@RequestMapping(value = "kitchens")
public class KitchenController {

	@Autowired
	private KitchenRepository kitchenRepository;

	@Autowired
	private KitchenRegisterService kitchenRegisterService;

	@GetMapping
	public List<Kitchen> listAll() {
		return kitchenRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Kitchen> findById(@PathVariable Long id) {

		var kitchen = kitchenRepository.findById(id);

		if (kitchen.isPresent()) {

			return ResponseEntity.ok(kitchen.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen save(@RequestBody Kitchen kitchen) {
		return kitchenRegisterService.save(kitchen);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen newKitchen) {

		var currentKitchen = kitchenRepository.findById(id);

		if (currentKitchen.isPresent()) {
			BeanUtils.copyProperties(newKitchen, currentKitchen, "id");

			kitchenRegisterService.save(currentKitchen.get());

			return ResponseEntity.ok(currentKitchen.get());
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Kitchen> remove(@PathVariable Long id) {

		try {
			kitchenRegisterService.remove(id);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (BindedEntityException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}

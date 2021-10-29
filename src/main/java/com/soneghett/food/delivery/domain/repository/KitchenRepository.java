package com.soneghett.food.delivery.domain.repository;

import java.util.List;

import com.soneghett.food.delivery.domain.model.Kitchen;

public interface KitchenRepository {

	List<Kitchen> listAll();

	Kitchen findById(Long id);

	Kitchen save(Kitchen kitchen);

	void remove(Kitchen kitchen);
}

package com.soneghett.food.delivery.domain.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.soneghett.food.delivery.domain.exception.BindedEntityException;
import com.soneghett.food.delivery.domain.model.Kitchen;
import com.soneghett.food.delivery.domain.repository.KitchenRepository;

@Service
public class KitchenRegisterService {

	@Autowired
	private KitchenRepository kitchenRepository;

	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}

	public void remove(Long id) {

		try {
			kitchenRepository.remove(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new BindedEntityException(
					String.format("Kitchen with id %d cannot be remove because it's been used", id));
		}
	}
}

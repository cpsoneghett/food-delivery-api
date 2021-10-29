package com.soneghett.food.delivery.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.soneghett.food.delivery.domain.model.Kitchen;
import com.soneghett.food.delivery.domain.repository.KitchenRepository;

public class KitchenRepositoryImpl implements KitchenRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Kitchen> listAll() {
		TypedQuery<Kitchen> query = em.createQuery("from Kitchen", Kitchen.class);

		return query.getResultList();
	}

	@Override
	public Kitchen findById(Long id) {
		return em.find(Kitchen.class, id);
	}

	@Override
	@Transactional
	public Kitchen save(Kitchen kitchen) {
		return em.merge(kitchen);
	}

	@Override
	@Transactional
	public void remove(Kitchen kitchen) {
		em.remove(kitchen);
	}

}

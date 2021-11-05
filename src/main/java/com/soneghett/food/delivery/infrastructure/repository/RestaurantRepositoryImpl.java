package com.soneghett.food.delivery.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.soneghett.food.delivery.domain.model.Restaurant;
import com.soneghett.food.delivery.domain.repository.RestaurantRepository;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Restaurant> listAll() {
		TypedQuery<Restaurant> query = em.createQuery("from Restaurant", Restaurant.class);

		return query.getResultList();
	}

	@Override
	public Restaurant findById(Long id) {
		return em.find(Restaurant.class, id);
	}

	@Override
	@Transactional
	public Restaurant save(Restaurant restaurant) {
		return em.merge(restaurant);
	}

}

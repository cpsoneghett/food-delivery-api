package com.soneghett.food.delivery.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.soneghett.food.delivery.domain.model.Restaurant;
import com.soneghett.food.delivery.domain.repository.RestaurantRepositoryQueries;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

	@PersistenceContext
	private EntityManager em;

	public List<Restaurant> find(String name, BigDecimal initialFreightRate, BigDecimal finalFreightRate) {

		var jpql = new StringBuilder();

		jpql.append("from Restaurant r where 0 = 0");

		Map<String, Object> params = new HashMap<>();

		if (StringUtils.hasLength(name)) {
			jpql.append(" and r.name like :name");
			params.put("name", "%" + name + "%");
		}

		if (initialFreightRate != null) {
			jpql.append(" and freightTax >= :initialFreightRate");
			params.put("initialFreightRate", initialFreightRate);
		}

		if (finalFreightRate != null) {
			jpql.append(" and freightTax <= :finalFreightRate");
			params.put("finalFreightRate", finalFreightRate);
		}

		TypedQuery<Restaurant> query = em.createQuery(jpql.toString(), Restaurant.class);

		params.forEach(query::setParameter);

		return query.getResultList();
	}
}

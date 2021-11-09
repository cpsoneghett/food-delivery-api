package com.soneghett.food.delivery.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.soneghett.food.delivery.domain.repository.CustomJpaRepository;

public class CustomJpaRepositoryImpl<T, U> extends SimpleJpaRepository<T, U> implements CustomJpaRepository<T, U> {

	private EntityManager em;

	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);

		this.em = entityManager;
	}

	@Override
	public Optional<T> findFirst() {

		var jpql = "from " + getDomainClass().getName();

		var entity = em.createQuery(jpql, getDomainClass()).setMaxResults(1).getSingleResult();

		return Optional.ofNullable(entity);
	}

}

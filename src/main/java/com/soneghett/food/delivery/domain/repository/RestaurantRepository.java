package com.soneghett.food.delivery.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.soneghett.food.delivery.domain.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryQueries {

	@Query("from Restaurant r where r.name like %:name% and r.kitchen.id = :id")
	List<Restaurant> findByNameAndKitchen(String name, @Param("id") Long kitchen);

}

package com.soneghett.food.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.soneghett.food.delivery.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class FoodDeliveryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryApiApplication.class, args);
	}

}

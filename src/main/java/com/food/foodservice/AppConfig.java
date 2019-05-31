package com.food.foodservice;

import com.food.foodservice.repository.FoodRepository;
import com.food.foodservice.repository.InMemoryFoodRepository;
import com.food.foodservice.repository.MongoFoodRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {

    @Bean
    @Profile("hash")
    public FoodRepository hashfoodRepository() {
        return new InMemoryFoodRepository();
    }

    @Bean
    @Profile("mongo")
    public FoodRepository mongoFoodRepository() {
        return new MongoFoodRepository();
    }



}



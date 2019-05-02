package com.food.foodservice.repository;

import java.util.List;

import com.food.foodservice.model.Food;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository<T> {
    Food getFood(String id);

    void addFood(String id, Food foodItem);

    List<Food> getAllFoods();

    void updateFood(String id);

    void removeFood(String id);
}

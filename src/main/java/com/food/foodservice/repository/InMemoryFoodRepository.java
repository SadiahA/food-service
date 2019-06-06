package com.food.foodservice.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.food.foodservice.model.Food;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryFoodRepository implements FoodRepository {
    private Map<String, Food> foods;

    public InMemoryFoodRepository() {
        foods  = new LinkedHashMap<>();
    }

    @Override
    public String addFood(Food foodItem) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        foods.put(randomUUIDString, foodItem);
        return randomUUIDString;
    }

    @Override
    public List<Food> getAllFoods() {
        List<Food> listOfFoods = new ArrayList<>();
        for (Map.Entry<String, Food> food : foods.entrySet()) {
            listOfFoods.add(food.getValue());
        }
        return listOfFoods;
    }

    @Override
    public Food getFood(String id) {
        return foods.get(id);
    }

    @Override
    public void updateFood(String id) {
        // TODO: Implement this
    }

    @Override
    public void removeFood(String id) {
        foods.remove(id);
    }
}

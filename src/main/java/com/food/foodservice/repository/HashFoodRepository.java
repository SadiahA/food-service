package com.food.foodservice.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.food.foodservice.model.Food;
import org.springframework.stereotype.Repository;

@Repository
public class HashFoodRepository implements FoodRepository {
    private Map<String, Food> foods;

    public HashFoodRepository() {
        foods  = new HashMap<>();
    }

    @Override
    public String addFood(Food foodItem) {
//        foods.put(foodItem);
        return "";
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
        return null;
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

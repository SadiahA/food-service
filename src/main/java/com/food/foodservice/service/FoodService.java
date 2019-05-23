package com.food.foodservice.service;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import com.food.foodservice.model.Food;
import com.food.foodservice.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public Optional<List<Food>> getAllFoods() {
        return Optional.ofNullable(foodRepository.getAllFoods());
    }

    public Optional<Food> getFoodById(String foodId) {
        return Optional.ofNullable(foodRepository.getFood(foodId));
    }

    public String addFood(@Valid Food food) {
        String foodId = foodRepository.addFood(food);
        return foodId;
    }

    public void deleteFood(String foodId) {
        foodRepository.removeFood(foodId);
    }

}

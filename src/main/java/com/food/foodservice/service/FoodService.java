package com.food.foodservice.service;

import java.util.List;
import javax.validation.Valid;

import com.food.foodservice.model.Food;
import com.food.foodservice.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public List<Food> retrieveAllFoods() {
        return foodRepository.getAllFoods();
    }

    public Food retrieveFood(String foodId) {
        return foodRepository.getFood(foodId);
    }

    public String addFood(@Valid Food food) {
        String uuid = food.getId();
        foodRepository.addFood(uuid, food);
        return uuid;
    }

}

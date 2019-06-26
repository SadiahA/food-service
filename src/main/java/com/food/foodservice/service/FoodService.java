package com.food.foodservice.service;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import com.food.foodservice.model.Food;
import com.food.foodservice.repository.FoodRepository;
import com.food.foodservice.view.FoodView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {


    private FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Optional<List<Food>> getAllFoods() {
        List<Food> foodList = foodRepository.getAllFoods();
        if(foodList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(foodList);
        }
    }

    public Optional<Food> getFoodById(String foodId) {
        return Optional.ofNullable(foodRepository.getFood(foodId));
    }

    public String addFood(@Valid FoodView foodView) throws InvalidFoodException {
        if (foodView != null) {
            Food food = new Food();

            food.setName(foodView.getName());
            food.setCost(foodView.getCost());
            food.setCalories(foodView.getCalories());
            food.setCategories(foodView.getCategories());

            return foodRepository.addFood(food);
        } else {
            throw new InvalidFoodException();
        }
    }

    public void deleteFood(String foodId) {
        foodRepository.removeFood(foodId);
    }
}

package com.food.foodservice.view;

import java.util.List;

import com.food.foodservice.model.Food;

public class FoodView {

    private Food food;

    public FoodView() {
    }

    public String getName() {
        return food.getName();
    }

    public List<String> getCategories() {
        return food.getCategories();
    }

    public double getCost() {
        return food.getCost();
    }

}

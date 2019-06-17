package com.food.foodservice.view;

import java.util.List;

import com.food.foodservice.model.Food;

public class DelegatingFoodView implements FoodView {

    private Food food;
    
    public DelegatingFoodView(Food food) {
        food = new Food();
    }

    public String getName() {
        return food.getName() + " - " + food.getCategories() + "kcal";
    }

    @Override
    public void setName(String name) {

    }

    public List<String> getCategories() {
        return food.getCategories();
    }

    @Override
    public void setCategories(List<String> categories) {

    }

    @Override
    public double getCalories() {
        return 0;
    }

    @Override
    public void setCalories(double calories) {

    }

    public double getCost() {
        return food.getCost();
    }

    @Override
    public void setCost(double cost) {

    }

}

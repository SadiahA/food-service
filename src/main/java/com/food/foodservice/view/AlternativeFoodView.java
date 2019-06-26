package com.food.foodservice.view;

import java.util.List;

import com.food.foodservice.model.Food;

public class AlternativeFoodView implements FoodView {

    private String name;
    private List<String> categories;
    private double calories;
    private double cost;

    public AlternativeFoodView() {
    }

    public AlternativeFoodView(Food food) {
        this.name = "[" + food.getName() + "]";  //just an example, name doesnt have to match food name
        this.categories = food.getCategories();
        this.calories = food.getCalories();
        this.cost = food.getCost();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getCategories() {
        return categories;
    }

    @Override
    public double getCalories() {
        return calories;
    }

    @Override
    public double getCost() {
        return cost;
    }

}

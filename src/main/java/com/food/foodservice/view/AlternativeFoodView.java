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
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<String> getCategories() {
        return categories;
    }

    @Override
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public double getCalories() {
        return 0;
    }

    @Override
    public void setCalories(double calories) {

    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }
}

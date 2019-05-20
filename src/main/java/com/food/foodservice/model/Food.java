package com.food.foodservice.model;

import java.util.List;
import java.util.UUID;

public class Food {

    private final transient String id = UUID.randomUUID().toString();
    private String name;
    private List<String> categories;
    private double calories;
    private double cost;

    public Food() {
    }

    public Food(String name, List<String> categories, long calories, double cost) {
        this.name = name;
        this.categories = categories;
        this.calories = calories;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}

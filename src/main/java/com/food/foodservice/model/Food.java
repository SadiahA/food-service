package com.food.foodservice.model;

import java.util.List;
import java.util.UUID;

public class Food {

    private String id;
    private String name;
    private List<String> categories;
    private double calories;
    private double cost;

    public Food() {
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

    public void setId(String id) {
        this.id = id;
    }
}

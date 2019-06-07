package com.food.foodservice.model;

import java.util.List;

public class Food {

    private String name;
    private List<String> categories;
    private double calories;
    private double cost;


    public Food() {
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


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Food{");
        sb.append("name='").append(name).append('\'');
        sb.append(", categories=").append(categories);
        sb.append(", calories=").append(calories);
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }



}
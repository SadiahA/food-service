package com.food.foodservice.view;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = UpdateFoodView.class)
public interface FoodView {
    public String getName();

    public List<String> getCategories();

    public double getCalories();

    public double getCost();
}


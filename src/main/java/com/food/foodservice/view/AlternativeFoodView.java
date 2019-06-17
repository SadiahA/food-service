package com.food.foodservice.view;

import java.util.List;

import com.food.foodservice.model.Food;

public class FoodView {

    private final String name;
    private final  List<String> categories;
    private final double calories;
    private final double cost;

    public FoodView(Food food) {
        this.name = "[" + food.getName() + "]";  //just an example, name doesnt have to match food name
        this.categories = food.getCategories()

    }


}

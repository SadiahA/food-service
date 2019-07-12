package com.food.foodservice.view;

import java.util.List;

import com.food.foodservice.model.Food;

public class UpdateFoodView implements FoodView {

    private Food food;

    public UpdateFoodView() {
    }

    public UpdateFoodView(Food food) {
        this.food = food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getName() {
        return food.getName() + " - " + food.getCalories() + "kcal";
    }

    public List<String> getCategories() {
        return food.getCategories();
    }

    @Override
    public double getCalories() {
        return food.getCalories();
    }

    public double getCost() {
        return food.getCost();
    }

    public String displayCost() {
        double cost  = food.getCost();

        if(cost % 10 == 0) {
            return String.format("£%.0f", cost);
        }

        if(cost < 1.00) {
            return Math.round(cost*100) + "p";
        }

        return "£" + String.format("%.2f", cost) + "p";
    }
}

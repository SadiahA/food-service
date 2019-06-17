package com.food.foodservice.view;

import java.util.ArrayList;
import java.util.List;

public interface FoodView {
    public String getName();

    public void setName(String name);

    public List<String> getCategories();

    public void setCategories(List<String> categories);

    public double getCalories();

    public void setCalories(double calories);

    public double getCost();

    public void setCost(double cost);

}




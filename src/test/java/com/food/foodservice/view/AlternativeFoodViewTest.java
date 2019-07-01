package com.food.foodservice.view;

import java.util.Arrays;
import java.util.List;

import com.food.foodservice.model.Food;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class AlternativeFoodViewTest {

    private Food food;

    private FoodView foodView;

    @Before
    public void setUp() throws Exception {
        food = new Food();
        food.setName("banana");
        food.setCost(0.50);
        food.setCalories(85);
        food.setCategories(Arrays.asList("fruit", "yellow", "potassium"));

        foodView = new AlternativeFoodView(food);
    }

    @Test
    public void getNameReturnsNameOfFoodObj() {
        // when
        String actualName = foodView.getName();

        // then
        assertEquals("[banana]", actualName);
    }

    @Test
    public void getCategoriesReturnsListOfCategoriesOfFoodViewObj() {
        // when
        List<String> actualCategories = foodView.getCategories();

        // then
        assertEquals(Arrays.asList("fruit", "yellow", "potassium"), actualCategories);
    }

    @Test
    public void getCaloriesReturnsCaloriesForFoodViewObj() {
        // when
        double calories = foodView.getCalories();

        // then
        assertEquals(85.0, calories, Double.NaN);
    }

    @Test
    public void getCostReturnsCostOfFoodViewObj() {
        // when
        double cost = foodView.getCost();

        // then
        assertEquals(0.5, cost, Double.NaN);
    }



}
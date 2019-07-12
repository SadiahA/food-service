package com.food.foodservice.view;

import java.util.Arrays;
import java.util.List;

import com.food.foodservice.model.Food;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DelegatingFoodViewTest {
    private Food food;

    private FoodView foodView;

    private void foodSetUp(Double price) {
        food = new Food();
        food.setName("grapes");
        food.setCost(price);
        food.setCalories(106);
        food.setCategories(Arrays.asList("fruit", "glucose"));

        foodView = new UpdateFoodView(food);
    }

    private void foodSetUp() {
        this.foodSetUp(1.50);
    }

    @Test
    public void getNameReturnsNameOfFoodObj() {
        // given
        foodSetUp();

        // when
        String actualName = foodView.getName();

        // then
        assertEquals("grapes - 106.0kcal", actualName);
    }

    @Test
    public void getCategoriesReturnsListOfCategoriesOfFoodViewObj() {
        // given
        foodSetUp();

        // when
        List<String> actualCategories = foodView.getCategories();

        // then
        assertEquals(Arrays.asList("fruit", "glucose"), actualCategories);
    }


    @Test
    public void getCaloriesReturnsCaloriesForFoodViewObj() {
        // given
        foodSetUp();

        // when
        double calories = foodView.getCalories();

        // then
        assertEquals(106, calories, Double.NaN);
    }


    @Test
    public void getCostReturnsCostOfFoodViewObj() {
        // given
        foodSetUp();

        // when
        double cost = foodView.getCost();

        // then
        assertEquals(1.5, cost, Double.NaN);
    }

    // add to interface
    @Test
    public void displayCostDisplaysCostAsAnIntegerWhenCostIsAMultipleOf10() {
        foodSetUp(30.0);
        assertEquals("£30", ((UpdateFoodView)foodView).displayCost());
    }

    @Test
    public void displayCostDisplaysMoneyWithPenceOnlyWhenCostIsLessThanOnePound() {
        foodSetUp(0.30);
        assertEquals("30p", ((UpdateFoodView)foodView).displayCost());
    }

    @Test
    public void displayCostDisplaysPoundsAndPence() {
        foodSetUp(7.21);
        assertEquals("£7.21p", ((UpdateFoodView)foodView).displayCost());
    }

    @Test
    public void displayCostDisplaysPoundAndPenceToTwoDecimalPlaces() {
        foodSetUp(1.56789);
        assertEquals("£1.57p", ((UpdateFoodView)foodView).displayCost());
    }

}
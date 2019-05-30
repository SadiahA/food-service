package com.food.foodservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.food.foodservice.model.Food;
import com.food.foodservice.repository.FoodRepository;
import com.food.foodservice.repository.HashFoodRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FoodServiceTest {

    private FoodService foodService;

    private FoodRepository hashFoodRepo;

    @Before
    public void setUp() throws Exception {
        hashFoodRepo = new HashFoodRepository();
        foodService = new FoodService(hashFoodRepo);
    }

    @Test
    public void getAllFoodsReturnsAnOptionalNonEmptyListOfFoodsIsAvailable() {
        // given
       Food food1 = new Food();
       Food food2 = new Food();
       Food food3 = new Food();

       List<Food> foodList = Arrays.asList(food1, food2, food3);

       hashFoodRepo.addFood(food1);
       hashFoodRepo.addFood(food2);
       hashFoodRepo.addFood(food3);

        // when
        Optional<List<Food>> actualResult = foodService.getAllFoods();

        // then
        assertEquals(Optional.of(foodList), actualResult);
    }

    @Test
    public void getAllFoodsReturnsAnOptionalEmptyListWhenListOfFoodsIsNotAvailable() {
        // given
        List<Food> foodList = new ArrayList<>();

        // when
        Optional<List<Food>> actualResult = foodService.getAllFoods();

        // then
        assertEquals(Optional.of(foodList), actualResult);
    }

    @Test
    public void getFoodByIdReturnsAnOptionalOfMatchingFoodItem() {
        // given
        Food food =  new Food();
        food.setName("lettuce");
        food.setCalories(40.0);
        food.setCost(60);
        food.setCategories(Arrays.asList("greens", "vegetable"));

        String id = hashFoodRepo.addFood(food);

        // when
        Optional<Food> actualResult = foodService.getFoodById(id);

        // then
        assertEquals(food, actualResult.get());
    }

    @Test
    public void getFoodByIdReturnsAnOptionalOfEmptyWhenFoodNotFound() {
        // given
        String id = "1234";

        // when
        Optional<Food> actualResult = foodService.getFoodById(id);

        // then
        assertEquals(Optional.empty(), actualResult);
    }

    @Test
    public void addFoodReturnsAFoodIdWhenValidFoodItemIsAdded() {
        // given
        Food food =  new Food();

        // when
        String actualResult = foodService.addFood(food);

        // then
       assertNotNull(actualResult);
    }

    // How to test an invalid food object? Validation done by @Valid

    @Test
    public void deleteFoodRemovesFoodItemFromRepository() {
        // given
        Food food = new Food();
        String foodId = hashFoodRepo.addFood(food);

        // then
        assertNotNull(foodService.getFoodById(foodId));

        // when
        foodService.deleteFood(foodId);

        // then
        assertEquals(Optional.empty(), foodService.getFoodById(foodId));
    }

    @Test
    public void deleteFoodReturnsNullIfFoodIdNotFound() {
     // can't test this as it doesn't go into the food service at all if the food item is not present
        // this is checked at the controller level
    }






}
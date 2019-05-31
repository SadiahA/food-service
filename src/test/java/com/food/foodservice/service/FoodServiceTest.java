package com.food.foodservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.food.foodservice.model.Food;
import com.food.foodservice.repository.FoodRepository;
import com.food.foodservice.repository.InMemoryFoodRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FoodServiceTest {

    private FoodService foodService;

    private FoodRepository inMemoryFoodRepo;

    @Before
    public void setUp() throws Exception {
        inMemoryFoodRepo = new InMemoryFoodRepository();
        foodService = new FoodService(inMemoryFoodRepo);
    }

    @Test
    public void getAllFoodsReturnsAnOptionalNonEmptyListOfFoodsIsAvailable() {
        // given
       Food food1 = new Food();
       Food food2 = new Food();
       Food food3 = new Food();

       List<Food> foodList = Arrays.asList(food1, food2, food3);

       inMemoryFoodRepo.addFood(food1);
       inMemoryFoodRepo.addFood(food2);
       inMemoryFoodRepo.addFood(food3);

        // when
        Optional<List<Food>> actualResult = foodService.getAllFoods();

        // then
        assertEquals(foodList, actualResult.get());
    }

    @Test
    public void getAllFoodsReturnsAnOptionalEmptyListWhenListOfFoodsIsNotAvailable() {
        // when
        Optional<List<Food>> actualResult = foodService.getAllFoods();

        // then
        assertEquals(Optional.empty(), actualResult);
    }

    @Test
    public void getFoodByIdReturnsAnOptionalOfMatchingFoodItem() {
        // given
        Food food =  new Food();
        food.setName("lettuce");
        food.setCalories(40.0);
        food.setCost(60);
        food.setCategories(Arrays.asList("greens", "vegetable"));

        String id = inMemoryFoodRepo.addFood(food);

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
    public void addFoodReturnsAFoodIdWhenAFoodItemIsAdded() throws InvalidFoodException {
        // given
        Food food =  new Food();

        // when
        String foodId = foodService.addFood(food);

        // then
       assertEquals(inMemoryFoodRepo.getFood(foodId), food);
    }

    @Test(expected = InvalidFoodException.class)
    public void addFoodThrowsExceptionWhenInvalidFoodItemIsAdded() throws InvalidFoodException {
        foodService.addFood(null);
    }

    @Test
    public void deleteFoodRemovesFoodItemFromRepository() {
        // given
        Food food = new Food();
        String foodId = inMemoryFoodRepo.addFood(food);

        // then
        assertNotNull(foodService.getFoodById(foodId));

        // when
        foodService.deleteFood(foodId);

        // then
        assertEquals(Optional.empty(), foodService.getFoodById(foodId));
        assertNull(inMemoryFoodRepo.getFood(foodId));
    }
}
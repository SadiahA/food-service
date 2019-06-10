package com.food.foodservice.repository;

import java.util.Arrays;

import com.food.foodservice.model.Food;
import org.junit.Test;
import static org.junit.Assert.*;

public class InMemoryFoodRepositoryTest {

    private InMemoryFoodRepository inMemoryFoodRepository;


    public InMemoryFoodRepositoryTest() {
        inMemoryFoodRepository = new InMemoryFoodRepository();
    }

    @Test
    public void addFoodAddsAValidFoodItemToInMemoryStore() {
        // given
        Food food = new Food();


        // when + then
        assertEquals(0, inMemoryFoodRepository.getAllFoods().size());
        inMemoryFoodRepository.addFood(food);
        assertEquals(1, inMemoryFoodRepository.getAllFoods().size());
    }

    @Test
    public void getAllFoodsReturnsAnEmptyListIfNoFoodItemsPresent() {
        // unsure if this test is necessary
        assertEquals(0, inMemoryFoodRepository.getAllFoods().size());
    }

    @Test
    public void getFoodReturnsAFoodItemForAMatchingId() {
        // given
        Food food = new Food();
        food.setName("Banana");
        food.setCalories(20);
        food.setCost(0.20);
        food.setCategories(Arrays.asList("Fruit"));

        String foodId = inMemoryFoodRepository.addFood(food);

        // when
        Food actualFood = inMemoryFoodRepository.getFood(foodId);

        // then
        assertEquals("Banana", actualFood.getName());
        assertEquals("Fruit",actualFood.getCategories().get(0));
    }

    @Test
    public void getFoodReturnsNullIfNoMatchingIdFoundForFoodItem() {
        // when
        Food actualFood = inMemoryFoodRepository.getFood("5cf4d04327f9dc03ce4df58e");

        // then
        assertNull(actualFood);
    }

    @Test
    public void removeFoodRemovesAFoodItemForAMatchingId() {
        // given
        Food food1 = new Food();
        Food food2 = new Food();

        String foodId1 = inMemoryFoodRepository.addFood(food1);
        inMemoryFoodRepository.addFood(food2);


        assertEquals(2, inMemoryFoodRepository.getAllFoods().size());

        // when
        inMemoryFoodRepository.removeFood(foodId1);
        assertEquals(1, inMemoryFoodRepository.getAllFoods().size());
    }

    @Test
    public void removeFoodDoesNotRemoveAFoodItemWhenNoMatchingFoodIdProvided() {
        // given
        Food food = new Food();

        inMemoryFoodRepository.addFood(food);
        assertEquals(1, inMemoryFoodRepository.getAllFoods().size());

        // when
        inMemoryFoodRepository.removeFood("5cf4d04327f9dc03ce4df58e");
        assertEquals(1, inMemoryFoodRepository.getAllFoods().size());
    }





}
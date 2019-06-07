package com.food.foodservice.repository;

import java.util.Arrays;

import com.food.foodservice.model.Food;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {MongoFoodRepository.class, FakeMongoDBConfig.class})
public class MongoFoodRepositoryTest {


    private MongoFoodRepository foodRepository;

    @Autowired
    private FakeMongoDBConfig fakeMongoDBConfig;

    @Before
    public void setUp() throws Exception {

        foodRepository = new MongoFoodRepository(fakeMongoDBConfig);

        Food food2 = new Food();
        Food food3 = new Food();
        Food food4 = new Food();

        foodRepository.addFood(food2);
        foodRepository.addFood(food3);
        foodRepository.addFood(food4);
    }

    @After
    public void tearDown() throws Exception {
        fakeMongoDBConfig.close();
    }

    @Test
    public void getFoodReturnsAFoodItemForAGivenId() {
        // given
        Food food = new Food();
        food.setCategories(Arrays.asList("fruit"));
        food.setCalories(45.0);
        food.setName("strawberries");

        String foodId = foodRepository.addFood(food);

        // when
        Food actualFood = foodRepository.getFood(foodId);

        // then
        assertEquals(food.getName(), actualFood.getName());
    }

    @Test
    public void getFoodReturnsNullIfForAnFoodItemWhichDoesNotExist() {
        // when
        Food food = foodRepository.getFood("5cf4d04327f9dc03ce4df58e");

        // then
        assertEquals(null, food);
    }

    @Test
    public void addFoodAddsFoodItemToMongoRepository() {
        // given
        MongoCollection<Document> collection = fakeMongoDBConfig
                .getMongoClient()
                .getDatabase("testdb")
                .getCollection("foodItems");

        Food food = new Food();
        food.setCategories(Arrays.asList("dairy"));
        food.setCalories(66.0);
        food.setName("milk");

        // when + then
        assertEquals(3, collection.countDocuments());
        foodRepository.addFood(food);
        assertEquals(4, collection.countDocuments());
    }

    @Test
    public void getAllFoodsReturnsTheListOfFoodsStoredInTheCollection() {
        // given
        Food food1 = new Food();
        Food food2 = new Food();

        // when + then
        assertEquals(3, foodRepository.getAllFoods().size());
        foodRepository.addFood(food1);
        foodRepository.addFood(food2);
        assertEquals(5, foodRepository.getAllFoods().size());
    }

    @Test
    public void removeFoodRemovesAFoodItemForAMatchingFoodId() {
        // given
        assertEquals(3, foodRepository.getAllFoods().size());

        String foodId = foodRepository.addFood(new Food());

        // when
        assertEquals(4, foodRepository.getAllFoods().size());
        foodRepository.removeFood(foodId);
        assertEquals(3, foodRepository.getAllFoods().size());
    }

    @Test
    public void removeFoodDoesNotRemoveAFoodItemWhenFoodIdDoesNotMatch() {
        // given
        assertEquals(3, foodRepository.getAllFoods().size());

        // when
        foodRepository.removeFood("5cf4d04327f9dc03ce4df58e");

        // then
        assertEquals(3, foodRepository.getAllFoods().size());
    }
}
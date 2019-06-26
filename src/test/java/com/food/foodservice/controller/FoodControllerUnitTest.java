package com.food.foodservice.controller;

import com.food.foodservice.model.Food;
import com.food.foodservice.service.FoodService;
import com.food.foodservice.service.InvalidFoodException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FoodControllerOtherTest {

    private FoodController foodController;

    private Food food;

    @Mock
    private FoodService foodService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        food = new Food();
        foodController = new FoodController(foodService);
    }

    @Test
    public void test1() throws InvalidFoodException {
        // given

        // when
        when(foodService.addFood(food)).thenReturn("1234");

        ResponseEntity<Link> linkResponseEntity = foodController.addFood(food);

        assertEquals(HttpStatus.OK, linkResponseEntity.getStatusCode());
        assertEquals("/food/1234", linkResponseEntity.getBody().getHref());
        assertFalse(linkResponseEntity.getBody().isTemplated());
    }
}

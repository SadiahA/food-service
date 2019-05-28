package com.food.foodservice.controller;

import java.util.Arrays;
import java.util.Optional;

import com.food.foodservice.model.Food;
import com.food.foodservice.service.FoodService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FoodControllerUnitTest {

    @Mock
    private FoodService foodService;

    @InjectMocks
    private FoodController foodController;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
//        this.mockMvc = MockMvcBuilders.standaloneSetup(foodController).build();
    }

    @Test
    public void canRetrieveFoodByIdWhenExists() {

        // given
        Food food = new Food();
        food.setName("courgette");
        food.setCategories(Arrays.asList("vegetables", "greens", "health-food"));
        food.setCost(20);
        food.setCalories(30);

        // when
        when(foodService.getFoodById("1234")).thenReturn(Optional.of(food));

        // then
//        this.mockMvc.perform("")
    }
}
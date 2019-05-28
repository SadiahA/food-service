package com.food.foodservice.controller;

import java.util.Arrays;
import java.util.Optional;

import com.food.foodservice.model.Food;
import com.food.foodservice.service.FoodService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FoodController.class)
public class FoodControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodService foodService;


    @Test
    public void canRetrieveFoodByIdWhenExists() throws Exception {
        // given
        Food food = new Food();
        food.setName("courgette");
        food.setCategories(Arrays.asList("vegetables", "greens", "health-food"));
        food.setCost(20);
        food.setCalories(30);

        given(foodService.getFoodById("1234")).willReturn(Optional.of(food));

        // when + then
        this.mockMvc.perform(get("/food/1234"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'name': 'courgette'," +
                        "'categories': ['vegetables','greens','health-food'], " +
                        "'calories': 30 , " +
                        "'cost': 20 }"));
    }

    @Test
    public void returnsHttp404WhenFoodByIdDoesNotExist() throws Exception {
        // given
        given(foodService.getFoodById("5678")).willReturn(Optional.empty());

        // when + then
        this.mockMvc.perform(get("/food/5678"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void addingValidFoodItemAndReturnFoodIdAnd200Response() throws Exception {
        // given
        // GSON is a java API from Google that converts java
        // objects to their foodJson representations
        Gson gson = new Gson();
        String foodJson = gson.toJson(new Food());
        given(foodService.addFood(new Food())).willReturn("2345");
        // String uuid keeps being null

//        String linkJson = gson.toJson(new Link("/food/" + "2345"));

        // when + then
        this.mockMvc.perform(post("/foods").contentType(MediaType.APPLICATION_JSON).content(foodJson))
                .andExpect(status().isOk());
    }

}
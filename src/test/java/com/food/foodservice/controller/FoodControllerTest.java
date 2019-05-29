package com.food.foodservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.food.foodservice.model.Food;
import com.food.foodservice.service.FoodService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FoodController.class)
public class FoodControllerTest {

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
        Gson gson = new Gson();
        String foodJson = gson.toJson(new Food());
        given(foodService.addFood(any(Food.class))).willReturn("2345");

        String linkJson = gson.toJson(new Link("/food/" + "2345"));

        // when + then
        this.mockMvc.perform(post("/foods").contentType(MediaType.APPLICATION_JSON).content(foodJson))
                .andExpect(status().isOk()).andExpect(content().string(linkJson));
    }

    @Test
    public void addingInValidFoodItemAndReturn400Response() throws Exception {
        // given
        Gson gson = new Gson();
        String invalidFoodJson = gson.toJson(new Food()).substring(0, 10);
        given(foodService.addFood(any(Food.class))).willReturn("2345");

        // when + then
        this.mockMvc.perform(post("/foods").contentType(MediaType.APPLICATION_JSON).content(invalidFoodJson))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void deletingAnExistingFoodItemAndReturns204Response() throws Exception {
        // given
        given(foodService.getFoodById(any(String.class))).willReturn(Optional.of(new Food()));

        // when + then
        this.mockMvc.perform(delete("/food/4567")).andExpect(status().isNoContent());
    }

    @Test
    public void deletingANonExistingFoodItemsReturnsA404Response() throws Exception {
        // given
        given(foodService.getFoodById(any(String.class))).willReturn(Optional.empty());

        // when + then
        this.mockMvc.perform(delete("/food/1234")).andExpect(status().isNotFound());
    }

    @Test
    public void canRetrieveAllFoodItemsWhenPresentAndReturns200Response() throws Exception {
        // given
        List<Food> foodList = new ArrayList<>();
        given(foodService.getAllFoods()).willReturn(Optional.of(foodList));

        // when + then
        this.mockMvc.perform(get("/foods")).andExpect(status().isOk());
    }

    @Test
    public void doesNotRetrieveAllFoodItemsWhenNotPresentAndReturns404Response() throws Exception {
        // given
        given(foodService.getAllFoods()).willReturn(Optional.empty());

        // when + then
        this.mockMvc.perform(get("/foods")).andExpect(status().isNotFound());
    }
}
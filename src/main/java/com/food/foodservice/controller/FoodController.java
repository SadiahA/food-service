package com.food.foodservice.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import com.food.foodservice.model.Food;
import com.food.foodservice.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/food/{foodId}")
    public ResponseEntity<Food> getFoodWithId(@PathVariable(name = "foodId") final String foodId) {
        final Optional<Food> food = foodService.getFoodById(foodId);
        return food.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/foods")
    public ResponseEntity<Link> addFood(@Valid @RequestBody Food food) {
        String uuid = foodService.addFood(food);
        Link link = new Link("/food/" + uuid);
        return new ResponseEntity(link, HttpStatus.OK);
    }

    @GetMapping("/foods")
    public ResponseEntity<List<Food>> getAllFoods() {
        Optional<List<Food>> allFoods = foodService.getAllFoods();
        if(allFoods.isPresent()) {
            return ResponseEntity.ok(allFoods.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/food/{foodId}")
    public ResponseEntity<Food> deleteFood(@PathVariable(name = "foodId") final String foodId) {
        Optional<Food> food = foodService.getFoodById(foodId);
        if(food.isPresent()) {
            foodService.deleteFood(foodId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


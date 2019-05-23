package com.food.foodservice.controller;

import java.util.Optional;
import javax.validation.Valid;

import com.food.foodservice.model.Food;
import com.food.foodservice.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        final Food food = foodService.retrieveFood(foodId);
        return Optional.ofNullable(food)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/foods")
    public ResponseEntity<Link> addFood(@Valid @RequestBody Food food) {
        String uuid = foodService.addFood(food);
        Link link = new Link("/food/" + uuid);
        return new ResponseEntity(link, HttpStatus.OK);
    }

}


package com.food.foodservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import com.food.foodservice.model.Food;
import com.food.foodservice.service.FoodService;
import com.food.foodservice.service.InvalidFoodException;
import com.food.foodservice.view.AlternativeFoodView;
import com.food.foodservice.view.FoodView;
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

    private FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/food/{foodId}")
    public ResponseEntity<FoodView> getFoodWithId(@PathVariable(name = "foodId") final String foodId) {
        final Optional<Food> food = foodService.getFoodById(foodId);

        //transforming food to foodview within optional box
        Optional<FoodView> foodView = food.map(f -> new AlternativeFoodView(f));

        return foodView.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/foods")
    public ResponseEntity<Link> addFood(@Valid @RequestBody FoodView foodView) {
        String uuid = null;
        try {
            uuid = foodService.addFood(foodView);

        } catch (InvalidFoodException e) {
            return ResponseEntity.badRequest().build();
        }
        Link link = new Link("/food/" + uuid);
        return new ResponseEntity(link, HttpStatus.OK);
    }

    @GetMapping("/foods")
    public ResponseEntity<List<FoodView>> getAllFoods() {
        Optional<List<Food>> allFoods = foodService.getAllFoods();

        // one liner urgh
        // Optional<List<AlternativeFoodView>> alternativeFoodViews = allFoods.map(foodList -> foodList.stream().map(f -> new AlternativeFoodView(f)).collect(Collectors.toList()));
        // transform list of foods to list of foodViews

        if(allFoods.isPresent()) {
            List<FoodView> foodViewList = new ArrayList<>();

            for (Food food : allFoods.get()) {
                FoodView foodView = new AlternativeFoodView(food);
                foodViewList.add(foodView);
            }
            return ResponseEntity.ok(foodViewList);
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


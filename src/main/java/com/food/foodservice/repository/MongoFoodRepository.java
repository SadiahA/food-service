package com.food.foodservice.repository;

import java.util.ArrayList;
import java.util.List;

import com.food.foodservice.model.Food;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class MongoFoodRepository implements FoodRepository {

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoFoodRepository() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("food");
//        database.createCollection("foodItems");
    }

    @Override
    public Food getFood(String id) {
        try {
            MongoCollection<Document> foodItems = database.getCollection("foodItems");

            BasicDBObject query = new BasicDBObject(); //represents query in search criteria
            query.put("id", new BasicDBObject("$eq", id));

            FindIterable<Document> cursor = foodItems.find(query);

            for (Document document : cursor) {
                return convertDocumentToFood(document);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void addFood(String id, Food foodItem) {
        Document document = new Document();
        document.put(id, foodItem);
        MongoCollection<Document> foodItems = database.getCollection("foodItems");
        foodItems.insertOne(document);
    }

    @Override
    public List<Food> getAllFoods() {
        List<FindIterable<Document>> listOfFoods = new ArrayList<>();
        MongoCollection<Document> foodItems = database.getCollection("foodItems");


        for (FindIterable<Document> food : listOfFoods) {
            listOfFoods.add(foodItems.find());
        }

        return null;
    }

    private Food convertDocumentToFood(Document document) {
        Food food = new Food();

        // name
        food.setName(document.get("name").toString());

        // categories
        List<String> categories = new ArrayList<>();
        String allCategories = document.get("categories").toString();

        List<String> categories1 = (List<String>) document.get("categories");
        String[] splitCategories = allCategories.split(",");

        int splitCategoriesArrayLength = splitCategories.length;
        int lengthOfLastCategory = splitCategories[2].trim().length();

        String firstCategory = splitCategories[0].trim().substring(1, splitCategories[0].length());
        String lastCategory = splitCategories[splitCategoriesArrayLength-1].substring(0, lengthOfLastCategory);

        categories.add(firstCategory);
        for(int i=1; i<splitCategoriesArrayLength-1; i++) {
            categories.add(splitCategories[i]);
        }
        categories.add(lastCategory);
        food.setCategories(categories);

        // calories
        String calories = (String) document.get("calories");
        food.setCalories(Math.round(Double.parseDouble(calories)));

        // cost
        String cost = document.get("cost").toString();
        food.setCost(Double.parseDouble(cost));

        return food;
    }



    @Override
    public void updateFood(String id) {
//        MongoCollection<Document> foodItems = database.getCollection("foodItems");
//
//        BasicDBObject query = new BasicDBObject();
//        query.put("id", new BasicDBObject("$eq", id));
//
//        FindIterable<Document> cursor = foodItems.find(query);
//
//        foodItems.updateOne()
//
//        for (Document document : cursor) {
//            document.get
//        }
    }

    @Override
    public void removeFood(String id) {
//        Bson bson = database.getCollection(
//        database.getCollection("foodItems").deleteOne(bson)
    }
}

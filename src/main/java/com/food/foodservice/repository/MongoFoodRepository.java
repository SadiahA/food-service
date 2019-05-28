package com.food.foodservice.repository;

import java.util.ArrayList;
import java.util.List;

import com.food.foodservice.model.Food;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class MongoFoodRepository implements FoodRepository {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private static final Gson GSON = new Gson();

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
            query.put("_id", new ObjectId(id));

            FindIterable<Document> cursor = foodItems.find(query);

            for (Document document : cursor) {
                return convertDocumentToFood(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String addFood(Food foodItem) {
        MongoCollection<Document> foodItems = database.getCollection("foodItems");

        Document document = new Document();
        document.append("name", foodItem.getName());
        document.append("categories", foodItem.getCategories());
        document.append("calories", foodItem.getCalories());
        document.append("cost", foodItem.getCost());

        foodItems.insertOne(document);

        return document.getObjectId("_id").toString();
    }

    @Override
    public List<Food> getAllFoods() {
        // TODO: Put a try catch in here and return null
        try {
            List<Food> foodList = new ArrayList<>();

            database.getCollection("foodItems");

            BasicDBObject searchQuery = new BasicDBObject();
            MongoCollection<Document> foodItems = database.getCollection("foodItems");


            FindIterable<Document> cursor = foodItems.find(searchQuery);

            for(Document document : cursor) {
                foodList.add(convertDocumentToFood(document));
            }

            return foodList;
        } catch (NullPointerException e) {
            return null;
        }
    }

    private Food convertDocumentToFood(Document document) {
        Food food = new Food();

        Object name = document.get("name");
        Object categories = document.get("categories");
        Object calories = document.get("calories");
        Object cost = document.get("cost");

        if(name != null) {
            food.setName(name.toString());
        }
        if(categories != null) {
            food.setCategories((List<String>) categories);
        }
        if(calories != null) {
            food.setCalories((Double) calories);
        }
        if(cost != null) {
            food.setCost(Double.parseDouble(cost.toString()));
        }
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
        MongoCollection<Document> foodItems = database.getCollection("foodItems");
        foodItems.deleteOne(new Document("_id", new ObjectId(id)));
    }
}

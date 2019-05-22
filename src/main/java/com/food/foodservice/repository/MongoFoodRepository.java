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
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

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
        List<FindIterable<Document>> listOfFoods = new ArrayList<>();
        MongoCollection<Document> foodItems = database.getCollection("foodItems");


        for (FindIterable<Document> food : listOfFoods) {
            listOfFoods.add(foodItems.find());
        }

        return null;
    }

    private Food convertDocumentToFood(Document document) {
        Food food = new Food();

        // id
        ObjectId id = document.getObjectId("_id");
        food.setId(id.toString());

        // name
        food.setName(document.get("name").toString());

        // categories
        List<String> categories = (List<String>) document.get("categories");
        food.setCategories(categories);

        // calories
        Double calories = (Double) document.get("calories");
        food.setCalories(calories);

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

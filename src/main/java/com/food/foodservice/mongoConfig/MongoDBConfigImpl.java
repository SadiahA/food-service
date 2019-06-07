package com.food.foodservice.mongoConfig;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class MongoDBConfigImpl implements MongoDBConfig {

    private static final int PORT = 27017;
    private static final String HOST_NAME = "127.0.0.1";
    private static final String DB_NAME = "food";


    private MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;


    public MongoDBConfigImpl() {
        mongoClient = new MongoClient(HOST_NAME, PORT);
        mongoDatabase = mongoClient.getDatabase(DB_NAME);
    }

    @Override
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    @Override
    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}


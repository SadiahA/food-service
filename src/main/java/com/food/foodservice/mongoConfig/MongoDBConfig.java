package com.food.foodservice.mongoConfig;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public interface MongoDBConfig {
//    @Bean
//    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    MongoClient getMongoClient();

    MongoDatabase getMongoDatabase();
}

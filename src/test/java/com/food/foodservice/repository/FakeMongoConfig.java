package com.food.foodservice.repository;

import com.food.foodservice.mongoConfig.MongoDBConfig;
import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {MongoFoodRepository.class})
public class FakeMongoConfig extends MongoDBConfig {

    private FakeMongoConfig() {
        super();
    }

    // I can't make this class extend AbstractMongoConfiguraton

    protected String getDatabaseName() {
        return "mockDB";
    }

    public Mongo mongo() {
        return new Fongo(getDatabaseName()).getMongo();
    }

//    @Bean
//    public MongoClient mongo() {
//        Fongo fongo = new Fongo("mockDB");
//        return fongo.getMongo();
//    }
}
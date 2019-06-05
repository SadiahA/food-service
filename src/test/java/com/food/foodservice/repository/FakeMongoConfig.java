package com.food.foodservice.repository;

import com.github.fakemongo.Fongo;
import com.mongodb.FongoDB;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackageClasses = {MongoFoodRepository.class})
public class FakeMongo {

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
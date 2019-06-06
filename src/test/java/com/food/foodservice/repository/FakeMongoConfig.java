package com.food.foodservice.repository;

import java.net.InetSocketAddress;

import com.food.foodservice.mongoConfig.MongoDBConfig;
import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {MongoFoodRepository.class})
public class FakeMongoConfig extends MongoDBConfig {

    private MongoClient client;
    private MongoServer server;


    protected FakeMongoConfig() {
        super();
        server = new MongoServer(new MemoryBackend());
        InetSocketAddress serverAddress = server.bind();

        client = new MongoClient(new ServerAddress(serverAddress));

    }

    // I can't make this class extend AbstractMongoConfiguraton



    protected String getDatabase() {
        return "foodDb";
    }

    public Mongo mongo() {
        return new Fongo(getDatabase()).getMongo();
    }

    public MongoServer getServer() {
        return server;
    }
//    @Bean
//    public MongoClient mongo() {
//        Fongo fongo = new Fongo("mockDB");
//        return fongo.getMongo();
//    }
}
package com.food.foodservice.repository;

import java.net.InetSocketAddress;

import com.food.foodservice.mongoConfig.MongoDBConfig;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.springframework.stereotype.Component;

@Component
public class FakeMongoDBConfig implements MongoDBConfig {

    private MongoServer mongoServer;
    private MongoClient mongoClient;

    public FakeMongoDBConfig() {
        mongoServer = new MongoServer(new MemoryBackend());
        InetSocketAddress serverAddress = mongoServer.bind();
        this.mongoClient = new MongoClient(new ServerAddress(serverAddress));
    }

    @Override
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    @Override
    public MongoDatabase getMongoDatabase() {
        return mongoClient.getDatabase("testdb");
    }

    public void close() {
        mongoClient.close();
        mongoServer.shutdown();
    }
}
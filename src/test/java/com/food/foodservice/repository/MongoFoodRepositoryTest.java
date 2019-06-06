package com.food.foodservice.repository;

import com.food.foodservice.model.Food;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class MongoFoodRepositoryTest {

    private MongoCollection<Document> collection;

    private FakeMongoConfig fakeMongoConfig;

    private MongoFoodRepository mongoFoodRepository;


    @Before
    public void setUp() throws Exception {

        collection = fakeMongoConfig.getMongoClient().getDatabase().getCollection("testcollection");

        mongoFoodRepository = new MongoFoodRepository(fakeMongoConfig);
    }

    @After
    public void tearDown() {
        fakeMongoConfig.getMongoClient().close();
        fakeMongoConfig.getServer().shutdown();
    }

    @Test
    public void getFoodReturnsAFoodItemForAGivenId() {
        // given
        Document document = new Document("_id", 1234);
        collection.insertOne(document);

        // when
        Food food = mongoFoodRepository.getFood("1234");

        // then
        assertEquals(new Food(), mongoFoodRepository);


    }

}

    @Configuration
    @EnableMongoRepositories
    protected static class TestConfiguration {
        @Bean
        public MongoTemplate mongoTemplate(MongoClient mongoClient) {
            return new MongoTemplate(mongoDbFactory(mongoClient));
        }

        @Bean
        public MongoDbFactory mongoDbFactory(MongoClient mongoClient) {
            return new SimpleMongoDbFactory(mongoClient, "test");
        }

        @Bean(destroyMethod="shutdown")
        public MongoServer mongoServer() {
            MongoServer mongoServer = new MongoServer(new MemoryBackend());
            mongoServer.bind();
            return mongoServer;
        }

        @Bean(destroyMethod="close")
        public MongoClient mongoClient(MongoServer mongoServer) {
            return new MongoClient(new ServerAddress(mongoServer.getLocalAddress()));
        }
    }








//    @Test
//    public void getFoodReturnsAFoodItemForAGivenId() {
//        mongoFoodRepository = new MongoFoodRepository(mongoDBConfig);
//
//        MongoDatabase fakeFoodDB = new Fongo("food").getDatabase("fakeFoodDB");
//        MongoCollection<Document> foodCollection = fakeFoodDB.getCollection("foodItems");
//
//        Document document1 = new Document();
//        document1.put("1234", new Food());
//        foodCollection.insertOne(document1);

//        Mongo mongo = Mockito.mock(Mongo.class);
//        MongoDatabase mongoDatabase = Mockito.mock(MongoDatabase.class);
//        MongoDBConfig mongoDBConfig = Mockito.mock(MongoDBConfig.class);
//        MongoCollection mongoCollection = Mockito.mock(MongoCollection.class);
//        Document document = Mockito.mock(Document.class);
//
//        given(this.mongoDBConfig.getMongoDatabase()).willReturn(fakeFoodDB);
//        given(mongoDBConfig.getMongoDatabase().getCollection("foodItems")).willReturn(foodCollection);
//
//         when
//        Food actualFood = mongoFoodRepository.getFood(  "1234");

        // then
//        assertEquals(, actualFood);
//    }

}
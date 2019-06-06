package com.food.foodservice.repository;

import java.util.Arrays;

import com.food.foodservice.model.Food;
import com.food.foodservice.mongoConfig.MongoDBConfig;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={MongoFoodRepositoryTest.TestConfiguration.class})
@ContextConfiguration( classes = {MongoFoodRepository.class})
public class MongoFoodRepositoryTest {

    @Autowired
    private FoodRepository foodRepository;

    @Mock
    private MongoDBConfig mongoDBConfig;

    @Before
    public void setUp() throws Exception {

        foodRepository = new MongoFoodRepository(mongoDBConfig);

        Food food2 = new Food();
        Food food3 = new Food();
        Food food4 = new Food();


        foodRepository.addFood(food2);
        foodRepository.addFood(food3);
        foodRepository.addFood(food4);
    }


    @Test
    public void getFoodReturnsAFoodItemForAGivenId() {
        // given
        Food food1 = new Food();
        food1.setCategories(Arrays.asList("fruit"));
        food1.setCalories(45.0);
        food1.setName("strawberries");

        String foodId = foodRepository.addFood(food1);

        // when
        Food actualFood = foodRepository.getFood(foodId);

        // then
        assertEquals(food1, actualFood);

    }

    @Configuration
    @EnableMongoRepositories(basePackageClasses={MongoFoodRepository.class})
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
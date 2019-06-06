package com.food.foodservice.repository;

import com.food.foodservice.model.Food;
import com.github.fakemongo.Fongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
public class MongoFoodRepositoryTest {

    private MongoFoodRepository mongoFoodRepository;

    private FakeMongoConfig mongoDBConfig;


    @Before
    public void setUp() throws Exception {
        initMocks(this);

    }


//    Mongo mongo = PowerMockito.mock(Mongo.class);
//    DB db = PowerMockito.mock(DB.class);
//    DBCollection dbCollection = PowerMockito.mock(DBCollection.class);
//
//PowerMockito.when(mongo.getDB("foo")).thenReturn(db);
//PowerMockito.when(db.getCollection("bar")).thenReturn(dbCollection);
//
//    MyService svc = new MyService(mongo); // Use some kind of dependency injection
//svc.getObjectById(1);
//
//PowerMockito.verify(dbCollection).findOne(new BasicDBObject("_id", 1));

    @Test
    public void getFoodReturnsAFoodItemForAGivenId() {
        mongoFoodRepository = new MongoFoodRepository(mongoDBConfig);

        MongoDatabase fakeFoodDB = new Fongo("food").getDatabase("fakeFoodDB");
        MongoCollection<Document> foodCollection = fakeFoodDB.getCollection("foodItems");

        Document document1 = new Document();
        document1.put("1234", new Food());
        foodCollection.insertOne(document1);

//        Mongo mongo = Mockito.mock(Mongo.class);
//        MongoDatabase mongoDatabase = Mockito.mock(MongoDatabase.class);
//        MongoDBConfig mongoDBConfig = Mockito.mock(MongoDBConfig.class);
//        MongoCollection mongoCollection = Mockito.mock(MongoCollection.class);
//        Document document = Mockito.mock(Document.class);
//
        given(this.mongoDBConfig.getMongoDatabase()).willReturn(fakeFoodDB);
        given(mongoDBConfig.getMongoDatabase().getCollection("foodItems")).willReturn(foodCollection);

        // when
        Food actualFood = mongoFoodRepository.getFood(  "1234");

        // then
//        assertEquals(, actualFood);
    }

}
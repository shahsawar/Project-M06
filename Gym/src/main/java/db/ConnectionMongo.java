package db;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ConnectionMongo implements Connexio<MongoCollection<Document>>{

    private static final String DATABASENAME = "gym";
    private static final String COLLECTIONNAME = "users";
    private static MongoClient mongoClient;

    public ConnectionMongo() {
    }

    /***
     * Test if mongoDB server is active
     * @throws Exception to be captured when user selects MongoDB database
     */
    public void test() throws Exception {
        MongoClientOptions.Builder o = MongoClientOptions.builder().connectTimeout(1).socketTimeout(1).serverSelectionTimeout(1500);//1.5 secs to do it fast
        Mongo mongo = new com.mongodb.MongoClient(new ServerAddress("localhost"),o.build());

        //Test with a ping
        DBObject ping = new BasicDBObject("ping", "1");
        mongo.getDB("gym").command(ping);
    }

    @Override
    public MongoCollection<Document> start() {
        mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase(DATABASENAME);
        MongoCollection<Document> collection = database.getCollection(COLLECTIONNAME);
        return collection;
    }

    @Override
    public void close() {
        mongoClient.close();
        System.out.println("Connection close successfully");
    }

    /*
    public static MongoCollection<Document> start() {
        mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase(DATABASENAME);
        MongoCollection<Document> collection = database.getCollection(COLLECTIONNAME);
        return collection;
    }

    public static void close(){
        System.out.println("Connection close successfully");
        mongoClient.close();
    }*/

}

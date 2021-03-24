package db;

import com.mongodb.client.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ConnectionMongo implements Connexio<MongoCollection<Document>>{

    private static final String DATABASENAME = "gym";
    private static final String COLLECTIONNAME = "users";
    private static MongoClient mongoClient;

    public ConnectionMongo() {
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

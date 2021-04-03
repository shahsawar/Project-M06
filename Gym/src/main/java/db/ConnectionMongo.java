package db;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import execptions.DatabaseNotAvailableExecption;
import org.bson.Document;
import utilities.Configuracio;
import utilities.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConnectionMongo implements Connexio<MongoCollection<Document>> {

    private static MongoClient mongoClient;

    public ConnectionMongo() {
    }

    /***
     * Test if mongoDB server is active
     */
    public void test() throws DatabaseNotAvailableExecption{

        try {

            MongoClientOptions.Builder o = MongoClientOptions.builder().connectTimeout(1).socketTimeout(1).serverSelectionTimeout(2000);//Default os 30s, put 2s to make it fast
            Mongo mongo = new com.mongodb.MongoClient(new ServerAddress(Configuracio.MONGO_SERVER_ADDRESS), o.build());

            //Test with a ping
            DBObject ping = new BasicDBObject("ping", "1");
            mongo.getDB(Configuracio.DBNAME).command(ping);
        } catch (Exception e){
            throw new DatabaseNotAvailableExecption();
        }

    }

    @Override
    public MongoCollection<Document> start() {
        MongoCollection<Document> collection;
        mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase(Configuracio.DBNAME);
        collection = database.getCollection(Configuracio.COLLECTION_USERS);

        return collection;
    }

    @Override
    public void close() throws DatabaseNotAvailableExecption {
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

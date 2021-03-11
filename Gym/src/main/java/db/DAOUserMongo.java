package db;
import com.mongodb.client.*;
import org.bson.Document;
import clases.User;

import java.util.List;

/***
 * @author shah
 */
public class DAOUserMongo implements DAOUser{

    public DAOUserMongo(){

    }

    @Override
    public void insert(User user) {
        MongoCollection<Document> collection = ConnectionMongo.start();
        collection.insertOne(toDoc(user));
        ConnectionMongo.close();
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getByIdentifier(Integer integer) {
        return null;
    }


    /***
     *
     * @param doc
     * @return
     */
    private User toClient(Document doc){
        User userTmp = new User();
        userTmp.setDni(doc.getString("dni"));
        userTmp.setName(doc.getString("name"));
        userTmp.setLastname(doc.getString("lastname"));
        userTmp.setAge(doc.getInteger("age"));
        userTmp.setUserCode(doc.getInteger("user_code"));
        return userTmp;
    }

    private Document toDoc(User user){
        Document docTmp = new Document();
        docTmp.append("dni", user.getDni());
        docTmp.append("name", user.getName());
        docTmp.append("lastname", user.getLastname());
        docTmp.append("age", user.getAge());
        docTmp.append("user_code", user.getUserCode());
        return docTmp;
    }


    /*
    public static void main(String[] args) {

        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = mongoClient.getDatabase("gym");
            MongoCollection<Document> collection = database.getCollection("users");

            User user = new User("12345678X", "shah", "sawar", 12, 1);
            collection.insertOne(toDoc(user));
        }

        User user = new User("12345678X", "shah", "sawar", 12, 1);
        DAOUserMongo d = new DAOUserMongo();
        d.insert(user);
    }*/
}

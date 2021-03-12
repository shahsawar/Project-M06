package db;
import com.mongodb.client.*;
import org.bson.Document;
import clases.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        List<User> userList = new ArrayList<>();
        MongoCollection<Document> collection = ConnectionMongo.start();
        try (MongoCursor<Document> cursor = collection.find().iterator()){
            while (cursor.hasNext()){
                Document docTmp = cursor.next();
                User userTmp = toUser(docTmp);
                userList.add(userTmp);
            }
        }
        ConnectionMongo.close();
        return userList;
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
    private User toUser(Document doc){
        User userTmp = new User();
        userTmp.setDni(doc.getString("dni"));
        userTmp.setName(doc.getString("name"));
        userTmp.setLastname(doc.getString("lastname"));
        userTmp.setBirthday(doc.getDate("birthday"));
        userTmp.setUserCode(doc.getInteger("user_code"));
        return userTmp;
    }

    private Document toDoc(User user){
        Document docTmp = new Document();
        docTmp.append("dni", user.getDni());
        docTmp.append("name", user.getName());
        docTmp.append("lastname", user.getLastname());
        docTmp.append("birthday", user.getBirthday());
        docTmp.append("user_code", user.getUserCode());
        return docTmp;
    }

    public String dateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }



    public static void main(String[] args) throws ParseException {
        /*
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = mongoClient.getDatabase("gym");
            MongoCollection<Document> collection = database.getCollection("users");

            User user = new User("12345678X", "shah", "sawar", 12, 1);
            collection.insertOne(toDoc(user));
        }*/

        /*
        User user = new User("1234", "user1", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("10-10-2020"), 1);
        User user2 = new User("1235", "user2", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("11-10-2020"), 2);
        User user3 = new User("1236", "user3", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("12-10-2020"), 3);
        User user4 = new User("1237", "user4", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("13-10-2020"), 4);

        DAOUserMongo d = new DAOUserMongo();
        d.insert(user);
        d.insert(user2);
        d.insert(user3);
        d.insert(user4);
        */

        DAOUserMongo dGetAll = new DAOUserMongo();
        List<User> userList = dGetAll.getAll();

        for (User u : userList) {
            System.out.println(u.getDni());
            System.out.println(u.getName());
            System.out.println(u.getLastname());
            System.out.println(u.getUserCode());
            System.out.println(dGetAll.dateToString(u.getBirthday()));
            System.out.println();
        }
    }
}

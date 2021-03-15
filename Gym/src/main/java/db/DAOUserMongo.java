package db;
import clases.Reservation;
import com.mongodb.client.*;
import org.bson.Document;
import clases.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

/***
 * @author shah
 */
public class DAOUserMongo implements DAOUser{

    public DAOUserMongo(){

    }

    @Override
    public void insert(User user) {
        user.setUserCode(getLastUserId()+1);
        MongoCollection<Document> collection = ConnectionMongo.start();
        collection.insertOne(toDoc(user));
        ConnectionMongo.close();
    }

    @Override
    public void delete(User user) {
        MongoCollection<Document> collection = ConnectionMongo.start();
        collection.deleteOne(toDoc(user));
        ConnectionMongo.close();
    }

    @Override
    public void update(User user, Integer integer) {
        MongoCollection<Document> collection = ConnectionMongo.start();
        collection.updateOne(eq("user_code", integer), combine(set("dni", user.getDni()), set("name",user.getName()),set("lastname",user.getLastname()),set("birthday",user.getBirthDate())));
        ConnectionMongo.close();
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
    public User getByIdentifier(Integer code) {
        MongoCollection<Document> collection = ConnectionMongo.start();
        Document mydoc = collection.find(eq("user_code", code)).first();
        User userTmp = toUser(mydoc);
        ConnectionMongo.close();
        return userTmp;
    }

    public List<Reservation> getAllReservations(int code){
        User userTmp = getByIdentifier(code);
        return userTmp.getReservations();
    }

    @Override
    public int getLastUserId() {
        MongoCollection<Document> collection = ConnectionMongo.start();
        Document myDoc = collection.find().sort(new Document("_id", -1)).first();
        if (myDoc == null){
            return 0;
        }
        ConnectionMongo.close();
        return myDoc.getInteger("user_code");
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
        userTmp.setBirthDate(doc.getDate("birthday"));
        userTmp.setUserCode(doc.getInteger("user_code"));
        if (doc.containsKey("reservations")){
            List<Document> listTmp = doc.getList("reservations", Document.class);
            List<Reservation> reservationList = new ArrayList<>();
            for (Document docTmp: listTmp) {
                reservationList.add(DAOReservationMongo.toReservation(docTmp));
            }
            userTmp.setReservations(reservationList);
        }
        return userTmp;
    }

    private Document toDoc(User user){
        Document docTmp = new Document();
        docTmp.append("dni", user.getDni());
        docTmp.append("name", user.getName());
        docTmp.append("lastname", user.getLastname());
        docTmp.append("birthday", user.getBirthDate());
        docTmp.append("user_code", user.getUserCode());
        docTmp.append("reservations", user.getReservations());
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
        }

        User user = new User("1234", "user1", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("10-10-2020"));
        User user2 = new User("1235", "user2", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("11-10-2020"));
        User user3 = new User("1236", "user3", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("12-10-2020"));
        User user4 = new User("1237", "user4", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("13-10-2020"));
        User user5 = new User("1237", "user4", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("13-10-2020"));
        DAOUserMongo d = new DAOUserMongo();
        d.insert(user);
        d.insert(user2);
        d.insert(user3);
        d.insert(user4);
        d.insert(user5);

        System.out.println("The last id " +d.getLastUserId());

        db.users.update(
           { user_code: 1 },
           { $push: { reservations: 1 } }
        );

        db.users.updateOne(
          { user_code: 1 },
          { $set: { "list.$[ele].reservation_code": 123 } },
          { arrayFilters: [ { "ele.user_code": 1 } ] }
        )

        db.users.find({$and:[{user_code:1}, {"reservations.workout_plane": true}]})
        */

        User userTmp = new User("123456789", "user1", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("13-10-2020"));
        DAOUserMongo mongo = new DAOUserMongo();
        mongo.update(userTmp, 1);


    }
}
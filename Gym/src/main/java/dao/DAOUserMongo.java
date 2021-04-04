package dao;

import clases.Reservation;
import com.mongodb.client.*;
import db.ConnectionMongo;
import exceptions.DatabaseNotAvailableExecption;
import exceptions.KeyException;
import org.bson.Document;
import clases.User;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static utilities.Converter.userToDoc;
import static utilities.Converter.toUser;

/***
 * @author shah
 */
public class DAOUserMongo implements DAOUser {


    /**
     * Constructs an empty DAOUserMongo

    public DAOUserMongo() {
    }*/


    /**
     * Insert an object of type {@link clases.User} in the database
     *
     * @param user {@link clases.User} to insert into database
     * @throws DatabaseNotAvailableExecption
     * @throws KeyException
     */
    @Override
    public void insert(User user) throws DatabaseNotAvailableExecption, KeyException {

        User userTmp = getUserByDNI(user.getDni());

        if (userTmp != null) {//User with the same dni already exist
            throw new KeyException();
        } else {
            //Create connection
            ConnectionMongo conn = new ConnectionMongo();

            user.setUserCode(getLastUserId() + 1);
            MongoCollection<Document> collection = conn.start();
            collection.insertOne(userToDoc(user));

            //Close Connection
            conn.close();
        }
    }


    /**
     * Removes an object of type {@link clases.User} from the database
     *
     * @param user {@link clases.User} to remove from database
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public void delete(User user) throws DatabaseNotAvailableExecption {
        //Create connection
        ConnectionMongo conn = new ConnectionMongo();

        MongoCollection<Document> collection = conn.start();
        collection.deleteOne(eq("user_code", user.getUserCode()));

        //Close Connection
        conn.close();
    }


    /**
     * Updates the object of type {@link clases.User} with identifier integer in the database
     *
     * @param user {@link clases.User} to update in database
     * @param integer
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public void update(User user, Integer integer) throws DatabaseNotAvailableExecption {
        //Create connection
        ConnectionMongo conn = new ConnectionMongo();

        MongoCollection<Document> collection = conn.start();
        collection.updateOne(eq("user_code", integer), combine(
                set("dni", user.getDni()),
                set("name", user.getName()),
                set("lastname", user.getLastname()),
                set("birthday", user.getBirthDate())));

        //Close connection
        conn.close();
    }


    /**
     * Returns a list of type {@link clases.User}
     *
     * @return Return a list of type {@link clases.User}
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public List<User> getAll() throws DatabaseNotAvailableExecption {
        //Create connection
        ConnectionMongo conn = new ConnectionMongo();

        List<User> userList = new ArrayList<>();
        MongoCollection<Document> collection = conn.start();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document docTmp = cursor.next();
                User userTmp = toUser(docTmp);
                userList.add(userTmp);
            }
        }

        //Close connection
        conn.close();
        return userList;
    }


    /**
     * Returns an object of type {@link clases.User}
     *
     * @param code identifier
     * @return return object {@link clases.User}
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public User getByIdentifier(Integer code) throws DatabaseNotAvailableExecption {
        //Create connection
        ConnectionMongo conn = new ConnectionMongo();

        MongoCollection<Document> collection = conn.start();
        Document mydoc = collection.find(eq("user_code", code)).first();
        User userTmp = toUser(mydoc);

        //Close connection
        conn.close();
        return userTmp;
    }


    @Override
    public int getLastUserId() throws DatabaseNotAvailableExecption {
        //Create connection
        ConnectionMongo conn = new ConnectionMongo();

        MongoCollection<Document> collection = conn.start();
        Document myDoc = collection.find().sort(new Document("_id", -1)).first();
        if (myDoc == null) {
            return 0;
        }
        conn.close();
        return myDoc.getInteger("user_code");
    }


    @Override
    public User getUserByDNI(String dni) throws DatabaseNotAvailableExecption {

        //Create connection
        ConnectionMongo conn = new ConnectionMongo();

        MongoCollection<Document> collection = conn.start();
        Document mydoc = collection.find(eq("dni", dni)).first();

        if (mydoc == null) {
            return null;
        }

        User userTmp = toUser(mydoc);

        //Close connection
        conn.close();
        return userTmp;
    }


    /**
     * Returns a list of type {@link clases.Reservation}
     *
     * @param code userCode
     * @return returns list of {@link clases.Reservation}
     * @throws DatabaseNotAvailableExecption
     */
    public List<Reservation> getAllReservations(int code) throws DatabaseNotAvailableExecption {
        User userTmp = getByIdentifier(code);
        return userTmp.getReservations();
    }
}
package dao;

import clases.Reservation;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import db.ConnectionMongo;
import exceptions.DatabaseNotAvailableExecption;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static java.util.Arrays.asList;
import static utilities.Converter.reservationToDoc;

public class DAOReservationMongo implements DAOReservation {


    /**
     * Insert an object of type {@link clases.Reservation} in the database
     *
     * @param reservation {@link clases.Reservation} to insert into database
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public void insert(Reservation reservation) throws DatabaseNotAvailableExecption {
        //Create connection
        ConnectionMongo conn = new ConnectionMongo();

        reservation.setReservationCode(getLastReservationCode(reservation.getUserCode())+1);
        MongoCollection<Document> collection = conn.start();
        collection.updateOne(eq("user_code", reservation.getUserCode()), Updates.push("reservations", reservationToDoc(reservation)));

        //Close connection
        conn.close();
    }


    /**
     * Removes an object of type {@link clases.Reservation} from the database
     *
     * @param reservation {@link clases.Reservation} to remove from database
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public void delete(Reservation reservation) throws DatabaseNotAvailableExecption {
        //Create connection
        ConnectionMongo conn = new ConnectionMongo();

        MongoCollection<Document> collection = conn.start();
        Bson filter = eq("user_code", reservation.getUserCode());
        Bson delete = Updates.pull("reservations", new Document("reservation_code", reservation.getReservationCode()));
        collection.updateOne(filter, delete);

        //Close connection
        conn.close();
    }


    /**
     * Updates the object of type {@link clases.Reservation} with identifier integer in the database
     *
     * @param reservation {@link clases.Reservation} to update in database
     * @param integer
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public void update(Reservation reservation, Integer integer) throws DatabaseNotAvailableExecption {
        //Create connection
        ConnectionMongo conn = new ConnectionMongo();

        MongoCollection<Document> collection = conn.start();
        Bson filter = eq("user_code", reservation.getUserCode());
        UpdateOptions options = new UpdateOptions().arrayFilters(asList(eq("ele.reservation_code", reservation.getReservationCode())));
        //Bson update = set("reservations.$[ele].workout_plane", false);
        Bson update = combine(
                set("reservations.$[ele].date",reservation.getDate()),
                set("reservations.$[ele].room_name",reservation.getRoomName()),
                set("reservations.$[ele].workout_plane",reservation.getWorkoutPlane())
        );
        collection.updateOne(filter, update, options);

        //Close connection
        conn.close();
    }

    /**
     *
     * @deprecated No need to use it in mongo
     */
    @Override
    public List<Reservation> getAll() {
        return null;
    }

    /**
     * @deprecated No need to use it in mongo
     */
    @Override
    public Reservation getByIdentifier(Integer integer) {
        return null;
    }

    /**
     * returns the last {@link clases.Reservation} code
     *
     * @param user_code User code
     * @return last {@link clases.Reservation} code
     * @throws DatabaseNotAvailableExecption
     */
    public int getLastReservationCode(int user_code) throws DatabaseNotAvailableExecption {
        //Create connection
        ConnectionMongo conn = new ConnectionMongo();

        int lastDoc = 0;
        MongoCollection<Document> collection = conn.start();
        Document mydoc = collection.find(eq("user_code", user_code)).first();
        if (mydoc.containsKey("reservations")) {
            lastDoc = mydoc.getList("reservations", Document.class).size();
        }
        conn.close();
        return lastDoc;
    }
}
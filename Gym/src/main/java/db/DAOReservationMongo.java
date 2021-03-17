package db;

import clases.Reservation;
import clases.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static java.util.Arrays.asList;

public class DAOReservationMongo implements DAOReservation {
    @Override
    public void insert(Reservation reservation) {
        reservation.setReservationCode(getLastUserId(reservation.getUserCode())+1);
        MongoCollection<Document> collection = ConnectionMongo.start();
        collection.updateOne(eq("user_code", reservation.getUserCode()), Updates.push("reservations", toDoc(reservation)));
        ConnectionMongo.close();
    }

    @Override
    public void delete(Reservation reservation) {
        MongoCollection<Document> collection = ConnectionMongo.start();
        collection.deleteOne(toDoc(reservation));
        ConnectionMongo.close();
    }

    @Override
    public void update(Reservation reservation, Integer integer) {
        MongoCollection<Document> collection = ConnectionMongo.start();

        Bson filter = eq("user_code", reservation.getUserCode());
        UpdateOptions options = new UpdateOptions().arrayFilters(asList(eq("ele.reservation_code", integer)));
        //Bson update = set("reservations.$[ele].workout_plane", false);
        Bson update = combine(
                set("reservations.$[ele].date",reservation.getDate()),
                set("reservations.$[ele].room_name",reservation.getRoomName()),
                set("reservations.$[ele].workout_plane",reservation.getWorkoutPlane())
        );
        collection.updateOne(filter, update, options);
        //UpdateResult result = collection.updateOne(filter, update, options);
        //System.out.println(result.toString());

        /*
        Document doc = collection.find(eq("reservations.reservation_code", 123)).first();
        System.out.println(doc.getInteger("reservation_code"));

        Document mydoc = collection.find(eq("user_code", reservation.getUserCode())).first();
        if (mydoc.containsKey("reservations")) {
            List<Document> listTmp = mydoc.getList("reservations", Document.class);

            for (Document documentTmp : listTmp) {
                if (documentTmp.getInteger("reservation_code") == integer){
                    System.out.println(documentTmp.getInteger("reservation_code"));
                    System.out.println(documentTmp.getInteger("user_code"));
                    System.out.println(documentTmp.getString("room_name"));
                }
            }

        }*/
        ConnectionMongo.close();
    }


    @Override
    public List<Reservation> getAll() {
        return null;
    }

    @Override
    public Reservation getByIdentifier(Integer integer) {
        return null;
    }



    public int getLastUserId(int user_code) {
        int lastDoc = 0;
        MongoCollection<Document> collection = ConnectionMongo.start();
        Document mydoc = collection.find(eq("user_code", user_code)).first();
        if (mydoc.containsKey("reservations")) {
            lastDoc = mydoc.getList("reservations", Document.class).size();
            System.out.println("This is last:"+lastDoc);

        }
        ConnectionMongo.close();
        return lastDoc;
    }

    public static Document toDoc(Reservation reservation) {
        Document docTmp = new Document();
        docTmp.append("reservation_code", reservation.getReservationCode());
        docTmp.append("date", reservation.getDate());
        docTmp.append("user_code", reservation.getUserCode());
        docTmp.append("room_name", reservation.getRoomName());
        docTmp.append("workout_plane", reservation.getWorkoutPlane());
        return docTmp;
    }

    public static Reservation toReservation(Document doc) {
        Reservation reservationTmp = new Reservation();
        reservationTmp.setReservationCode(doc.getInteger("reservation_code"));
        reservationTmp.setDate(doc.getDate("date"));
        reservationTmp.setUserCode(doc.getInteger("user_code"));
        reservationTmp.setRoomName(doc.getString("room_name"));
        reservationTmp.setWorkoutPlane(doc.getBoolean("workout_plane"));
        return reservationTmp;
    }


    public static void main(String[] args) throws ParseException {
        Reservation r = new Reservation(123, new SimpleDateFormat("dd-MM-yyyy").parse("10-10-2020"), 1, "sala fitness", true);
        DAOReservationMongo d = new DAOReservationMongo();
        d.insert(r);
        //d.update(r, 123);
        /*
        MongoCollection<Document> collection = ConnectionMongo.start();
        Document myDoc = collection.find(eq("reservations.user_code", 1)).sort(new Document("_id", -1)).first();+

        System.out.println(myDoc.getInteger("reservation_code"));

        */

        ConnectionMongo.close();
    }

}
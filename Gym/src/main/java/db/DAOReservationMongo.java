package db;

import clases.Reservation;
import clases.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class DAOReservationMongo implements DAOReservation {
    @Override
    public void insert(Reservation reservation) {
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

    }

    @Override
    public List<Reservation> getAll() {
        return null;
    }

    @Override
    public Reservation getByIdentifier(Integer integer) {
        return null;
    }


    public int getLastUserId(User user) {
        MongoCollection<Document> collection = ConnectionMongo.start();
        Document myDoc = collection.find(eq("reservations_user_code", 1)).sort(new Document("_id", -1)).first();
        if (myDoc == null){
            return 0;
        }
        ConnectionMongo.close();
        return myDoc.getInteger("user_code");
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
        Reservation r = new Reservation(123, new SimpleDateFormat("dd-MM-yyyy").parse("10-10-2021"), 1, "SALA FITNESS", false);
        DAOReservation d = new DAOReservationMongo();
        d.update(r, r.getUserCode());

        MongoCollection<Document> collection = ConnectionMongo.start();
        Document myDoc = collection.find(eq("reservations.user_code", 1)).sort(new Document("_id", -1)).first();

        System.out.println(myDoc.getInteger("reservation_code"));



        ConnectionMongo.close();
        System.out.println();
    }

}
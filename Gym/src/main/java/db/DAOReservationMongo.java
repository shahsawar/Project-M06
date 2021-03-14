package db;

import clases.Reservation;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

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
        Reservation r = new Reservation(124, new SimpleDateFormat("dd-MM-yyyy").parse("10-10-2020"), 1, "sala fitness", true);
        DAOReservation d = new DAOReservationMongo();
        d.insert(r);
    }
}
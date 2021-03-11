package db;

import clases.Reservation;
import clases.WorkoutPlane;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class DAOReservationMongo implements DAOReservation{
    @Override
    public void insert(Reservation reservation) {
        MongoCollection<Document> collection = ConnectionMongo.start();
        collection.insertOne(toDoc(reservation));
        ConnectionMongo.close();
    }

    @Override
    public void delete(Reservation reservation) {

    }

    @Override
    public void update(Reservation reservation) {

    }

    @Override
    public List<Reservation> getAll() {
        return null;
    }

    @Override
    public Reservation getByIdentifier(Integer integer) {
        return null;
    }

    private Document toDoc(Reservation reservation){
        Document docTmp = new Document();
        docTmp.append("reservation_code", reservation.getReservationCode());
        docTmp.append("date", reservation.getDate());
        docTmp.append("user_code", reservation.getUserCode());
        docTmp.append("room_name", reservation.getRoomName());
        docTmp.append("workout_plane", DAOWorkoutPlaneMongo.toDoc(reservation.getWorkoutPlane()));
        return docTmp;
    }

    public static void main(String[] args) throws ParseException {
        Reservation r = new Reservation(123, new SimpleDateFormat("dd-MM-yyyy").parse("10-10-2020"),1233, "sala fitness", new WorkoutPlane("sprint", 2));
        DAOReservation d = new DAOReservationMongo();
        d.insert(r);
    }
}
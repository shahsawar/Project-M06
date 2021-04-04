package utilities;


import clases.Reservation;
import clases.User;
import org.bson.Document;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @autor shah
 */
public class Converter {



    /**
     * Date to String
     * @param date
     * @return String
     */
    public static String dateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }


    /**
     * Convert String to date
     *
     * @param strDate String
     * @return Date
     */
    public static Date stringToDate(String strDate) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            //Parsing the String
            fecha = formatter.parse(strDate);

        } catch (ParseException e) {
            Log.severe("Error, can't parse date " + strDate);
        }
        return fecha;
    }


    /**
     * Convert Boolean to Integer
     *
     * @param booleano
     * @return Integer
     */
    public static int booleanToInteger(boolean booleano) {
        return booleano ? 1 : 0;
    }


    /***
     * Convert a document to {@link clases.User}
     *
     * @param doc {@link clases.User} document
     * @return return {@link clases.User}
     */
    public static User toUser(Document doc) {
        User userTmp = new User();
        userTmp.setDni(doc.getString("dni"));
        userTmp.setName(doc.getString("name"));
        userTmp.setLastname(doc.getString("lastname"));
        userTmp.setBirthDate(doc.getDate("birthday"));
        userTmp.setUserCode(doc.getInteger("user_code"));
        if (doc.containsKey("reservations")) {
            List<Document> listTmp = doc.getList("reservations", Document.class);
            List<Reservation> reservationList = new ArrayList<>();
            for (Document docTmp : listTmp) {
                reservationList.add(toReservation(docTmp));
            }
            userTmp.setReservations(reservationList);
        }
        return userTmp;
    }


    /**
     * Convert {@link clases.User} to document
     *
     * @param user {@link clases.User}
     * @return Document
     */
    public static Document userToDoc(User user) {
        Document docTmp = new Document();
        docTmp.append("dni", user.getDni());
        docTmp.append("name", user.getName());
        docTmp.append("lastname", user.getLastname());
        docTmp.append("birthday", user.getBirthDate());
        docTmp.append("user_code", user.getUserCode());
        docTmp.append("reservations", user.getReservations());
        return docTmp;
    }


    /**
     * Convert {@link clases.Reservation} to Doc
     *
     * @param reservation {@link clases.Reservation}
     * @return Document
     */
    public static Document reservationToDoc(Reservation reservation) {
        Document docTmp = new Document();
        docTmp.append("reservation_code", reservation.getReservationCode());
        docTmp.append("date", reservation.getDate());
        docTmp.append("user_code", reservation.getUserCode());
        docTmp.append("room_name", reservation.getRoomName());
        docTmp.append("workout_plane", reservation.getWorkoutPlane());
        return docTmp;
    }


    /**
     * Convert document to {@link clases.Reservation}
     *
     * @param doc Document
     * @return {@link clases.Reservation}
     */
    public static Reservation toReservation(Document doc) {
        Reservation reservationTmp = new Reservation();
        reservationTmp.setReservationCode(doc.getInteger("reservation_code"));
        reservationTmp.setDate(doc.getDate("date"));
        reservationTmp.setUserCode(doc.getInteger("user_code"));
        reservationTmp.setRoomName(doc.getString("room_name"));
        reservationTmp.setWorkoutPlane(doc.getBoolean("workout_plane"));
        return reservationTmp;
    }

}

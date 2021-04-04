package dao;

import clases.Reservation;
import db.ConnexioJDBC;
import exceptions.DatabaseNotAvailableExecption;
import utilities.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static utilities.Converter.booleanToInteger;
import static utilities.Converter.stringToDate;

/***
 * @author ronald
 */

public class DAOReservationJDBC implements DAOReservation {

    /**
     * Insert an object of type {@link clases.Reservation} in the database
     *
     * @param reservation {@link clases.Reservation} to insert into database
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public void insert(Reservation reservation) throws DatabaseNotAvailableExecption {
        ConnexioJDBC connexioJDBC = new ConnexioJDBC();

        int insercion = 0;
        java.sql.Date sqlDate = new java.sql.Date(reservation.getDate().getTime()); //Fecha en formato sql

        int lastReservationCode = getLastReservationCode();
        if (lastReservationCode == -1) {
            lastReservationCode = 1;
        } else {
            lastReservationCode++;
        }
        reservation.setReservationCode(lastReservationCode);

        String sentenciaSQL = "INSERT INTO reservation (code, user_code, room_name, has_workout_plane, date) VALUES (?,?,?,?,?)";
        PreparedStatement sentenciaPreparada = null;

        try {
            sentenciaPreparada = connexioJDBC.start().prepareStatement(sentenciaSQL);
            sentenciaPreparada.setInt(1, reservation.getReservationCode());
            sentenciaPreparada.setInt(2, reservation.getUserCode());
            sentenciaPreparada.setString(3, reservation.getRoomName());
            sentenciaPreparada.setString(4, booleanToInteger(reservation.getWorkoutPlane()) + ""); //Booleano para formato SQL
            sentenciaPreparada.setString(5, sqlDate + "");
            insercion = sentenciaPreparada.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }

        if (insercion == 1) {
            Log.info("Reservation added");
        } else {
            Log.info("An error occurred while inserting the reservation num. " + reservation.getReservationCode());
        }
    }


    /**
     * Removes an object of type {@link clases.Reservation} from the database
     *
     * @param reservation {@link clases.Reservation} to remove from database
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public void delete(Reservation reservation) throws DatabaseNotAvailableExecption {
        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        try {

            String sentenciaSQL = "DELETE FROM reservation WHERE code = " + reservation.getReservationCode();
            PreparedStatement preparedStatement = connexioJDBC.start().prepareStatement(sentenciaSQL);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }
    }


    /**
     * Updates the object of type {@link clases.Reservation} with identifier integer in the database
     *
     * @param reservation {@link clases.Reservation} to update in database
     * @param reservationCode
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public void update(Reservation reservation, Integer reservationCode) throws DatabaseNotAvailableExecption {
        ConnexioJDBC connexioJDBC = new ConnexioJDBC();

        java.sql.Date sqlDate = new java.sql.Date(reservation.getDate().getTime()); //Fecha en formato sql
        try {

            PreparedStatement updateEXP = connexioJDBC.start().prepareStatement("UPDATE reservation SET room_name = ?, has_workout_plane = ?, date = ? WHERE code = " + reservationCode);
            updateEXP.setString(1, reservation.getRoomName());
            updateEXP.setString(2, booleanToInteger(reservation.getWorkoutPlane()) + ""); //Booleano para formato SQL
            updateEXP.setString(3, sqlDate + "");
            int result = updateEXP.executeUpdate();
            Log.info("Reservation " + reservationCode + "has been updated");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }
    }

    /**
     * Returns a list of type {@link clases.Reservation}
     *
     * @return Returns a list of {@link clases.Reservation}
     * @throws DatabaseNotAvailableExecption
     * @deprecated No need to use this method
     */
    @Override
    public List<Reservation> getAll() throws DatabaseNotAvailableExecption {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        Statement statement = null;
        List<Reservation> reservations = new ArrayList<>();
        try {
            statement = connexioJDBC.start().createStatement();
            String sentenciaSQL = "SELECT *  FROM reservation;";
            ResultSet rs = statement.executeQuery(sentenciaSQL);

            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationCode(rs.getInt("code"));
                reservation.setUserCode(rs.getInt("user_code"));
                reservation.setRoomName(rs.getString("room_name"));
                reservation.setWorkoutPlane(rs.getBoolean("has_workout_plane"));
                reservation.setDate(stringToDate(rs.getString("date")));
                reservations.add(reservation);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }
        return reservations;
    }


    /**
     * Returns an object of type {@link clases.Reservation}
     *
     * @param integer identifier
     * @return return object {@link clases.Reservation}
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public Reservation getByIdentifier(Integer integer) throws DatabaseNotAvailableExecption {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        Statement statement = null;
        Reservation reservation = new Reservation();
        try {
            statement = connexioJDBC.start().createStatement();
            String sentenciaSQL = "SELECT *  FROM reservation WHERE code = " + integer;
            ResultSet rs = statement.executeQuery(sentenciaSQL);

            while (rs.next()) {
                reservation.setReservationCode(rs.getInt("code"));
                reservation.setUserCode(rs.getInt("user_code"));
                reservation.setRoomName(rs.getString("room_name"));
                reservation.setWorkoutPlane(rs.getBoolean("has_workout_plane"));
                reservation.setDate((Date) rs.getDate("date"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }

        return reservation;

    }

    /**
     * Returns a list of {@link clases.Reservation} for a specific user.
     * @param user_code user code
     * @return return {@link clases.Reservation} list
     * @throws DatabaseNotAvailableExecption
     */
    public List<Reservation> getUserReservations(int user_code) throws DatabaseNotAvailableExecption {
        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        Statement statement = null;
        List<Reservation> reservations = new ArrayList<>();
        try {
            statement = connexioJDBC.start().createStatement();
            String sentenciaSQL = "SELECT *  FROM reservation WHERE user_code = " + user_code;
            ResultSet rs = statement.executeQuery(sentenciaSQL);

            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationCode(rs.getInt("code"));
                reservation.setUserCode(rs.getInt("user_code"));
                reservation.setRoomName(rs.getString("room_name"));
                reservation.setWorkoutPlane(rs.getBoolean("has_workout_plane"));
                reservation.setDate(stringToDate(rs.getString("date")));

                reservations.add(reservation);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }
        return reservations;
    }

    /**
     * returns the last {@link clases.Reservation} code
     *
     * @return last {@link clases.Reservation} code
     * @throws DatabaseNotAvailableExecption
     */
    public int getLastReservationCode() throws DatabaseNotAvailableExecption {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        Statement statement = null;
        int lastCode = 0;
        try {
            statement = connexioJDBC.start().createStatement();
            String sentenciaSQL = "SELECT *  FROM reservation ORDER BY code DESC LIMIT 1;";//Recogemos el último registro
            ResultSet rs = statement.executeQuery(sentenciaSQL);

            int contador = 0;

            while (rs.next()) {

                lastCode = rs.getInt("code");
                contador++;
            }

            if (contador > 0) {
            } else {
                lastCode = -1;//La tabla está vacía
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }

        return lastCode;
    }

}

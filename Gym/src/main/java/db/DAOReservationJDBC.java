package db;

import clases.Reservation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * @author ronald
 */

public class DAOReservationJDBC implements DAOReservation{

    //TODO Poner este método en otra clase para aprovachar que tambien se usa en DAOUserJDBC
    private Date stringToDate(String strDate) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;

        try {
            //Parsing the String
            fecha = formatter.parse(strDate);

        } catch (ParseException e) {

            System.out.println("Error: " + e);
        }


        return fecha;
    }

    static int booleanToInteger(boolean booleano) {

        return  booleano ? 1 : 0;
    }

    @Override
    public void insert(Reservation reservation) {
        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        connexioJDBC.start();

        int insercion = 0;
        java.sql.Date sqlDate = new java.sql.Date(reservation.getDate().getTime()); //Fecha en formato sql

        int lastReservationCode = getLastReservationCode();
        if (lastReservationCode == -1 ){
            lastReservationCode = 1;
        } else {
            lastReservationCode++;
        }
        reservation.setReservationCode(lastReservationCode);

        String sentenciaSQL = "INSERT INTO reservation (code, user_code, room_name, has_workout_plane, date) VALUES (?,?,?,?,?)";
        PreparedStatement sentenciaPreparada = null;

        try {
            sentenciaPreparada = connexioJDBC.con.prepareStatement(sentenciaSQL);
            sentenciaPreparada.setInt(1,reservation.getReservationCode());
            sentenciaPreparada.setInt(2,reservation.getUserCode());
            sentenciaPreparada.setString(3,reservation.getRoomName());
            sentenciaPreparada.setString(4,booleanToInteger(reservation.hasWorkoutPlane()) + ""); //Booleano para formato SQL
            sentenciaPreparada.setString(5,sqlDate + "");
            insercion = sentenciaPreparada.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }

        if (insercion == 1) {
            System.out.println("Reserva añadida correctamente");
        } else {
            System.out.println("Ha ocurrido un error al insertar la reserva num. " + reservation.getReservationCode());
        }
    }

    @Override
    public void delete(Reservation reservation) {
        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        connexioJDBC.start();
        try {

            String sentenciaSQL = "DELETE FROM reservation WHERE code = " + reservation.getReservationCode();
            PreparedStatement preparedStatement = connexioJDBC.con.prepareStatement(sentenciaSQL);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }
    }

    @Override
    public void update(Reservation reservation, Integer integer) {
        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        connexioJDBC.start();

        java.sql.Date sqlDate = new java.sql.Date(reservation.getDate().getTime()); //Fecha en formato sql
        try {

            PreparedStatement updateEXP = connexioJDBC.con.prepareStatement("UPDATE reservation SET user_code = ?, room_name = ?, has_workout_plane = ?, date = ?");
            updateEXP.setInt(1, reservation.getUserCode());
            updateEXP.setString(2, reservation.getRoomName());
            updateEXP.setString(3, booleanToInteger(reservation.hasWorkoutPlane()) + ""); //Booleano para formato SQL
            updateEXP.setString(4, sqlDate + "");
            int result = updateEXP.executeUpdate();
            System.out.println(result + " rows updated");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }
    }

    @Override
    public List<Reservation> getAll() {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        connexioJDBC.start();
        Statement statement = null;
        List<Reservation> reservations = new ArrayList<>();
        try {
            statement = connexioJDBC.con.createStatement();
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

    @Override
    public Reservation getByIdentifier(Integer integer) {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        connexioJDBC.start();
        Statement statement = null;
        Reservation reservation = new Reservation();
        try {
            statement = connexioJDBC.con.createStatement();
            String sentenciaSQL = "SELECT *  FROM reservation WHERE code = " + integer;
            ResultSet rs = statement.executeQuery(sentenciaSQL);

            while (rs.next()) {
                reservation.setReservationCode(rs.getInt("code"));
                reservation.setUserCode(rs.getInt("user_code"));
                reservation.setRoomName(rs.getString("room_name"));
                reservation.setWorkoutPlane(rs.getBoolean("has_workout_plane"));
                reservation.setDate((Date)rs.getDate("date"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }

        return reservation;

    }


    public List<Reservation> getUserReservations(int user_code){
        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        connexioJDBC.start();
        Statement statement = null;
        List<Reservation> reservations = new ArrayList<>();
        try {
            statement = connexioJDBC.con.createStatement();
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


    public int getLastReservationCode() {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        connexioJDBC.start();
        Statement statement = null;
        int lastCode = 0;
        try {
            statement = connexioJDBC.con.createStatement();
            String sentenciaSQL = "SELECT *  FROM reservation ORDER BY code DESC LIMIT 1;";//Recogemos el último registro
            ResultSet rs = statement.executeQuery(sentenciaSQL);

            int contador = 0;

            while (rs.next()) {

                lastCode = rs.getInt("code");
                contador++;
            }

            if(contador > 0) {
                System.out.println("Se han recuperado " + contador + " reserva(s)");
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

package db;

import clases.Reservation;
import clases.User;
import dao.DAOReservationJDBC;
import dao.DAOUserJDBC;
import exceptions.DatabaseNotAvailableExecption;
import exceptions.KeyException;
import utilities.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * @author ronald
 */
public class GestorPersistenciaJDBC extends GestorPersistencia {

    DAOUserJDBC daoUserJDBC = new DAOUserJDBC();
    DAOReservationJDBC daoReservationJDBC = new DAOReservationJDBC();

    @Override
    public void insertUser(User user) throws DatabaseNotAvailableExecption, KeyException {
        daoUserJDBC.insert(user);
    }

    @Override
    public void updateUser(User user, Integer user_code) {
        try {
            daoUserJDBC.update(user, user_code);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            System.out.println("Could not update user in database!");
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not update the user in database!\n" + sw.toString());
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            daoUserJDBC.delete(user);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            System.out.println("Could not delete user from database!");
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not delete user from database!\n" + sw.toString());
        }
    }

    @Override
    public User getUserById(Integer id) {
        try {
            return daoUserJDBC.getByIdentifier(id);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            System.out.println("Could not find user with id = " + id + " in database!");
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not find the user with id = " + id + " in database!\n" + sw.toString());
        }
        return null;
    }

    @Override
    public User getUserByDNI(String dni) {
        try {
            return daoUserJDBC.getUserByDNI(dni);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            System.out.println("Could not find user with dni = " + dni + " in database!");
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not find the user with dni = " + dni + " in database!\n" + sw.toString());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return daoUserJDBC.getAll();
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            System.out.println("Could not get all users from database!");
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not get all users from database!\n" + sw.toString());
        }
        return null;
    }

    @Override
    public void insertReservation(Reservation reservation) {
        try {
            daoReservationJDBC.insert(reservation);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            System.out.println("Could not insert the reservation in database!");
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not insert the reservation in database!\n" + sw.toString());
        }
    }

    @Override
    public void updateReservation(Reservation reservation, Integer reservation_code) {
        try {
            daoReservationJDBC.update(reservation, reservation_code);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            System.out.println("Could not update reservation in database!");
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not update reservation in database!\n" + sw.toString());
        }
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        try {
            daoReservationJDBC.delete(reservation);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            System.out.println("Could not delete reservation from database!");
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not delete reservation from database!\n" + sw.toString());
        }
    }

    @Override
    public Reservation getReservationById(Integer id) {
        try {
            return daoReservationJDBC.getByIdentifier(id);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            System.out.println("Could not find the reservation with id = " + id + " in database!");
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not find the reservation with id = " + id + " in database!\n" + sw.toString());
        }
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() {
        try {
            return daoReservationJDBC.getAll();
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            System.out.println("Could not get all reservations from database!");
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not get all reservations from database!\n" + sw.toString());
        }
        return null;
    }

    @Override
    public List<Reservation> getReservationsByUser(User user) {

        try {
            return daoReservationJDBC.getUserReservations(user.getUserCode());
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            System.out.println("Could not get all reservations by user from database!");
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not get all reservations by user from database!\n" + sw.toString());
        }

        return null;
    }
}

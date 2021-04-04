package db;

import clases.Reservation;
import clases.User;
import dao.DAOReservationMongo;
import dao.DAOUserMongo;
import execptions.DatabaseNotAvailableExecption;
import execptions.KeyException;
import utilities.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

public class GestorPersistenciaMongo extends GestorPersistencia{

    DAOUserMongo daoUserMongo = new DAOUserMongo();
    DAOReservationMongo daoReservationMongo = new DAOReservationMongo();

    @Override
    public void insertUser(User user) throws DatabaseNotAvailableExecption, KeyException {
        daoUserMongo.insert(user);
    }

    @Override
    public void updateUser(User user, Integer user_code) {
        try {
            daoUserMongo.update(user, user_code);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not update user in database!\n" + sw.toString());
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            daoUserMongo.delete(user);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not delete user from database!\n" + sw.toString());
        }
    }

    @Override
    public User getUserById(Integer id) {
        try {
            return daoUserMongo.getByIdentifier(id);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not find user with id = " + id + " in database!\n" + sw.toString());
        }
        return null;
    }

    @Override
    public User getUserByDNI(String dni) {
        try {
            return daoUserMongo.getUserByDNI(dni);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not find user with dni = " + dni + " in database!\n" + sw.toString());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return daoUserMongo.getAll();
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not get all users from database!\n" + sw.toString());
        }
        return null;
    }

    @Override
    public void insertReservation(Reservation reservation) {
        try {
            daoReservationMongo.insert(reservation);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not insert the reservation in database!\n" + sw.toString());
        }
    }

    @Override
    public void updateReservation(Reservation reservation, Integer reservation_code) {
        try {
            daoReservationMongo.update(reservation, reservation_code);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not update reservation in database!\n" + sw.toString());
        }
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        try {
            daoReservationMongo.delete(reservation);
        } catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not delete reservation from database!\n" + sw.toString());
        }
    }

    @Override
    public Reservation getReservationById(Integer id) {
        return daoReservationMongo.getByIdentifier(id);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return daoReservationMongo.getAll();
    }

    @Override
    public List<Reservation> getReservationsByUser(User user) {
        try{
            return daoUserMongo.getAllReservations(user.getUserCode());
        }  catch (DatabaseNotAvailableExecption databaseNotAvailableExecption) {
            StringWriter sw = new StringWriter();
            databaseNotAvailableExecption.printStackTrace(new PrintWriter(sw));
            Log.severe("\nCould not get reservation from database!\n" + sw.toString());
        }

        return null;
    }
}

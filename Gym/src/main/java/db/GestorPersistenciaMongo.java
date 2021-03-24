package db;

import clases.Reservation;
import clases.User;

import java.util.List;

public class GestorPersistenciaMongo extends GestorPersistencia{

    DAOUserMongo daoUserMongo = new DAOUserMongo();
    DAOReservationMongo daoReservationMongo = new DAOReservationMongo();

    @Override
    public void insertUser(User user) {
        daoUserMongo.insert(user);
    }

    @Override
    public void updateUser(User user, Integer user_code) {
        daoUserMongo.update(user, user_code);
    }

    @Override
    public void deleteUser(User user) {
        daoUserMongo.delete(user);
    }

    @Override
    public User getUserById(Integer id) {
        return daoUserMongo.getByIdentifier(id);
    }

    @Override
    public User getUserByDNI(String dni) {
        return daoUserMongo.getUserByDNI(dni);
    }

    @Override
    public List<User> getAllUsers() {
        return daoUserMongo.getAll();
    }

    @Override
    public void insertReservation(Reservation reservation) {
        daoReservationMongo.insert(reservation);
    }

    @Override
    public void updateReservation(Reservation reservation, Integer reservation_code) {
        daoReservationMongo.update(reservation, reservation_code);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        daoReservationMongo.delete(reservation);
    }

    @Override
    public Reservation getReservationById(Integer id) {
        return daoReservationMongo.getByIdentifier(id);
    }
}

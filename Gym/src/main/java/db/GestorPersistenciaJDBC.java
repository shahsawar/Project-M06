package db;

import clases.Reservation;
import clases.User;

public class GestorPersistenciaJDBC extends GestorPersistencia{

    DAOUserJDBC daoUserJDBC = new DAOUserJDBC();
    DAOReservationJDBC daoReservationJDBC = new DAOReservationJDBC();

    @Override
    public void insertUser(User user) {
        daoUserJDBC.insert(user);
    }

    @Override
    public void updateUser(User user, Integer user_code) {
        daoUserJDBC.update(user, user_code);
    }

    @Override
    public void deleteUser(User user) {
        daoUserJDBC.delete(user);
    }

    @Override
    public User getUserById(Integer id) {
        return daoUserJDBC.getByIdentifier(id);
    }

    @Override
    public void insertReservation(Reservation reservation) {
        daoReservationJDBC.insert(reservation);
    }

    @Override
    public void updateReservation(Reservation reservation, Integer reservation_code) {
        daoReservationJDBC.update(reservation, reservation_code);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        daoReservationJDBC.delete(reservation);
    }

    @Override
    public Reservation getReservationById(Integer id) {
        return daoReservationJDBC.getByIdentifier(id);
    }
}

package db;

import clases.Reservation;
import clases.User;

import java.util.List;

public abstract class GestorPersistencia {

    public abstract void insertUser(User user);
    public abstract void updateUser(User user, Integer user_code);
    public abstract void deleteUser(User user);
    public abstract User getUserById(Integer id);
    public abstract User getUserByDNI(String dni);
    public abstract List<User> getAllUsers();

    public abstract void insertReservation(Reservation reservation);
    public abstract void updateReservation(Reservation reservation, Integer reservation_code);
    public abstract void deleteReservation(Reservation reservation);
    public abstract Reservation getReservationById(Integer id);

}

package db;

import clases.Reservation;
import clases.User;
import exceptions.DatabaseNotAvailableExecption;
import exceptions.KeyException;

import java.util.List;

/**
 * Abstract class that has access to the DAO classes methods
 * @author Ronald, Shah
 */
public abstract class GestorPersistencia {

    /**
     * Inserts a {@link clases.User} in the database
     * @param user {@link clases.User} object
     * @throws DatabaseNotAvailableExecption
     * @throws KeyException if an user with the same DNI is founded
     */
    public abstract void insertUser(User user) throws DatabaseNotAvailableExecption, KeyException;

    /**
     * Updates a {@link clases.User} in the database
     * @param user {@link clases.User} object
     * @param user_code
     */
    public abstract void updateUser(User user, Integer user_code);

    /**
     * Deletes a {@link clases.User} from the database
     * @param user
     */
    public abstract void deleteUser(User user);

    /**
     * Gets a {@link clases.User} with the provided id
     * @param id
     * @return {@link clases.User} if founded
     */
    public abstract User getUserById(Integer id);

    /**
     * Gets a {@link clases.User} with the provided dni
     * @param dni
     * @return {@link clases.User} if founded
     */
    public abstract User getUserByDNI(String dni);

    /**
     * Gets all the users in the database
     * @return a list with all the users in the database
     */
    public abstract List<User> getAllUsers();

    /**
     * Inserts a {@link clases.Reservation} in the database
     * @param reservation
     */
    public abstract void insertReservation(Reservation reservation);

    /**
     * Updates a {@link clases.Reservation} in the database
     * @param reservation {@link clases.Reservation} object
     * @param reservation_code id of the {@link clases.Reservation}
     */
    public abstract void updateReservation(Reservation reservation, Integer reservation_code);

    /**
     * Deletes a {@link clases.Reservation} from the database
     * @param reservation {@link clases.Reservation} object
     */
    public abstract void deleteReservation(Reservation reservation);

    /**
     * Gets a {@link clases.Reservation} with the provided id
     * @param id {@link clases.Reservation}'s identifier
     * @return {@link clases.Reservation} if founded
     */
    public abstract Reservation getReservationById(Integer id);

    /**
     * Gets all the {@link clases.Reservation} of the database
     * @deprecated Is never used
     * @return a list of all the {@link clases.Reservation} in the database
     */
    public abstract List<Reservation> getAllReservations();

    /**
     * Gets a list of {@link clases.Reservation} from the specified {@link clases.User}
     * @param user {@link clases.User} object from which we extract the {@link clases.Reservation} list
     * @return a list of all the {@link clases.Reservation} from the user if founded
     */
    public abstract List<Reservation> getReservationsByUser( User user);

}

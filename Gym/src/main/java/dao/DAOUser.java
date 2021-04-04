package dao;

import clases.User;
import exceptions.DatabaseNotAvailableExecption;

/**
 * @author Shah, Ronald
 */
public interface DAOUser extends DAO<User, Integer>{

    /**
     * returns the id of the last {@link clases.User}
     * @return last user id
     * @throws DatabaseNotAvailableExecption
     */
    public int getLastUserId() throws DatabaseNotAvailableExecption;

    /**
     * Returns an object of type {@link clases.User}
     * @param dni ID of the {@link clases.User} to search in the database
     * @return User
     * @throws DatabaseNotAvailableExecption
     */
    public User getUserByDNI(String dni) throws DatabaseNotAvailableExecption;
}
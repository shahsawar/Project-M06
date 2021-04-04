package dao;

import clases.User;
import execptions.DatabaseNotAvailableExecption;

/**
 * @author Shah, Ronald
 */
public interface DAOUser extends DAO<User, Integer>{

    /**
     * returns the id of the last user
     * @return last user id
     * @throws DatabaseNotAvailableExecption
     */
    public int getLastUserId() throws DatabaseNotAvailableExecption;

    /**
     * Returns an object of type user
     * @param dni ID of the user to search in the database
     * @return User
     * @throws DatabaseNotAvailableExecption
     */
    public User getUserByDNI(String dni) throws DatabaseNotAvailableExecption;
}
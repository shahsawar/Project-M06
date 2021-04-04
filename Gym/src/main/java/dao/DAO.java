package dao;

import execptions.DatabaseNotAvailableExecption;
import execptions.KeyException;

import java.util.List;

/**
 * @param <T> T - the type of element in this DAO
 * @param <I> I - the identifier of the object to update or search.
 * @author Ronald, Shah
 */

public interface DAO<T, I> {


    /**
     * Insert an object of type T into the database
     *
     * @param t object to insert into database
     * @throws DatabaseNotAvailableExecption
     * @throws KeyException
     */
    public void insert(T t) throws DatabaseNotAvailableExecption, KeyException;


    /**
     * Removes an object of type T from the database
     *
     * @param t object to remove from database
     * @throws DatabaseNotAvailableExecption
     */
    public void delete(T t) throws DatabaseNotAvailableExecption;


    /**
     * Updates the object of type T with identifier I in the database
     *
     * @param t object to update in database
     * @param i user Id
     * @throws DatabaseNotAvailableExecption
     */
    public void update(T t, I i) throws DatabaseNotAvailableExecption;


    /**
     * Return a list of type T
     *
     * @return returns a list of type T
     * @throws DatabaseNotAvailableExecption
     */
    public List<T> getAll() throws DatabaseNotAvailableExecption;


    /**
     * Returns an object of type T
     *
     * @param i identifier
     * @return return object T
     * @throws DatabaseNotAvailableExecption
     */
    public T getByIdentifier(I i) throws DatabaseNotAvailableExecption;
}
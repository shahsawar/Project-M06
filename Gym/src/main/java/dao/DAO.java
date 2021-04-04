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
    //Insert
    public void insert(T t) throws DatabaseNotAvailableExecption, KeyException;

    //Delete
    public void delete(T t) throws DatabaseNotAvailableExecption;

    //Update
    public void update(T t, I i) throws DatabaseNotAvailableExecption;

    //GetAll
    public List<T> getAll() throws DatabaseNotAvailableExecption;

    //Get by identifier
    public T getByIdentifier(I i) throws DatabaseNotAvailableExecption;
}
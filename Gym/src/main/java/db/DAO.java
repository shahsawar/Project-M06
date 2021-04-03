package db;

import execptions.DatabaseNotAvailableExecption;

import java.util.List;

public interface DAO <T, I>{

    //Insert
    public void insert(T t) throws DatabaseNotAvailableExecption;

    //Delete
    public void delete(T t) throws DatabaseNotAvailableExecption;

    //Update
    public void update(T t, I i) throws DatabaseNotAvailableExecption;

    //GetAll
    public List<T> getAll() throws DatabaseNotAvailableExecption;

    //Get by identifier
    public T getByIdentifier(I i) throws DatabaseNotAvailableExecption;


}
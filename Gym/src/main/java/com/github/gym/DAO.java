package com.github.gym;

import java.util.List;

public interface DAO <T, I>{

    //Insert
    public void insert(T t);

    //Delete
    public void delete(T t);

    //Update
    public void update(T t);

    //GetAll
    public List<T> getAll();

    //Get by identifier
    public T getByIdentifier(I i);

}
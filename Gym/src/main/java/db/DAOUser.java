package db;

import clases.User;

public interface DAOUser extends DAO<User, Integer>{

    public int getLastUserId();

}
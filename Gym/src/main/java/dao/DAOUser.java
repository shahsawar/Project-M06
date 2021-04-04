package dao;

import clases.User;
import execptions.DatabaseNotAvailableExecption;

public interface DAOUser extends DAO<User, Integer>{

    public int getLastUserId() throws DatabaseNotAvailableExecption;
    public User getUserByDNI(String dni) throws DatabaseNotAvailableExecption;
}
package db;

import execptions.DatabaseNotAvailableExecption;

public interface Connexio<T> {
    public T start() throws DatabaseNotAvailableExecption;
    public void close() throws DatabaseNotAvailableExecption;
}

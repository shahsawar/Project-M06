package db;

import exceptions.DatabaseNotAvailableExecption;

/**
 *
 * @author Ronald, Shah
 * @param <T> T - the type of element
 */
public interface Connexio<T> {
    /**
     * Starts a database connection
     * @return T the connection Object
     * @throws DatabaseNotAvailableExecption
     */
    public T start() throws DatabaseNotAvailableExecption;

    /**
     * Close the database connection
     * @throws DatabaseNotAvailableExecption
     */
    public void close() throws DatabaseNotAvailableExecption;
}

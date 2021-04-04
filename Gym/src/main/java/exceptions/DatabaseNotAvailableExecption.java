package exceptions;

/**
 * @autor shah
 */
public class DatabaseNotAvailableExecption extends Exception {

    /**
     * Constructs a new exception with null as a detail message. if the database does not exist or is not connected.
     */
    public DatabaseNotAvailableExecption() {};

    /**
     * Constructs a new exception with the specified detail message. if the database does not exist or is not connected.
     *
     * @param errorMessage
     */
    public DatabaseNotAvailableExecption(String errorMessage) {
        super(errorMessage);
    };

}

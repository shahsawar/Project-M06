package exceptions;

/**
 * @autor shah
 */
public class KeyException extends Exception {

    /**
     * Constructs a new exception with null as a detail message. if the key already exists it exists in the database.
     */
    public KeyException() {
    };

    /**
     * Constructs a new exception with the specified detail message. if the key already exists it exists in the database.
     *
     * @param errorMessage
     */
    public KeyException(String errorMessage) {
        super(errorMessage);
    }

    ;

}

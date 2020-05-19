package application.exceptions;

/**
 * Represent a exception when an unsupported operation is asked.
 */
public class UnsupportedOperationException extends Exception {

    /**
     * Parameterized constructor.
     * @param message The error message.
     */
    public UnsupportedOperationException(String message) {
        super(message);
    }
}

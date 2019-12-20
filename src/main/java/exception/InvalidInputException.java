package exception;

/**
 * Exception used for when an input is invalid.
 */
public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

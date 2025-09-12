package baymaxx.exception;

/**
 * Custom exception class for Baymaxx application errors.
 * Used to signal specific issues encountered during execution.
 */
public class BaymaxxException extends Exception {

    /**
     * Constructs a BaymaxxException with a specified error message.
     * @param message The detail message describing the exception
     */
    public BaymaxxException(String message) {
        super(message);
    }

    /**
     * Returns the error message associated with this exception.
     * @return The exception message string
     */
    public String printMessage() {
        return this.getMessage();
    }

}

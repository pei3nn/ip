package finixx.exception;

/**
 * Custom exception class for Finixx application errors.
 * Used to signal specific issues encountered during execution.
 */
public class FinixxException extends Exception {

    /**
     * Constructs a FinixxException with a specified error message.
     * @param message The detail message describing the exception
     */
    public FinixxException(String message) {
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

/**
 * Exception for the SMBoard class in case violates the contract made by SMBoard.
 */

public class BoardException extends Exception {

    private final String message;

    /**
     * Inherit the methods of the super class Exception and set the message field.
     * @param message Message of the exception.
     */
    public BoardException(String message) {
        super();
        this.message = message;
    }

    /**
     * Converts the boardException to a string representation.
     * @return String
     */
    @Override
    public String toString() {
        return "BoardException: " + message;
    }
}

/**
 * Exception for the SMBoard class in case violates the contract made by SMBoard.
 */

public class SMBoardException extends Exception {

    private final String message;

    /**
     * Inherit the methods of the super class Exception and set the message field.
     * @param message message to be printed along with toString method, for readability's sake.
     */
    public SMBoardException(String message) {
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

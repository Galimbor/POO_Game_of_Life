/**
 * Exception for the SparseMatrix class in case violates the contract made by SparseMatrix.
 */
public class SparseMatrixException extends Exception {

    private final String message;


    /**
     * Inherit the methods of the super class Exception and set the message field.
     *
     * @param message message to be printed along with toString method, for readability's sake.
     */
    public SparseMatrixException(String message) {
        super();
        this.message = message;
    }


    /**
     * Converts the SparseMatrixException to a string representation.
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return "SparseMatrixException : " + message;
    }
}

public class SparseMatrixException extends Exception {

    private final String message;

    /**
     *
     * @param message message to be printed along with toString method, for readability's sake.
     */
    public SparseMatrixException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String toString() {
        return "SparseMatrixException : " + message;
    }
}

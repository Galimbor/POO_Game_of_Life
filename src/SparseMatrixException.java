public class SparseMatrixException extends Exception {

    private final String message;

    public SparseMatrixException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String toString() {
        return "SparseMatrixException : " + message;
    }
}

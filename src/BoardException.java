public class BoardException extends Exception {

    private final String message;

    public BoardException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String toString() {
        return "BoardException: " + message;
    }
}

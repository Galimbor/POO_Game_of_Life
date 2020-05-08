public class SentinelLLException extends Exception {

    private final String message;

    public SentinelLLException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String toString() {
        return "SentinelLLException: " + message;
    }
}

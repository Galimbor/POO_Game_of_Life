public class SentinelLLException extends Exception {

    private final String message;

    /**
     *
     * @param message message to be printed along with toString method, for readability's sake.
     */
    public SentinelLLException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String toString() {
        return "SentinelLLException: " + message;
    }
}

package belajar.java.error;

public class BlankException extends RuntimeException {
    public BlankException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

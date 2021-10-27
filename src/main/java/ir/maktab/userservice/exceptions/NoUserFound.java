package ir.maktab.userservice.exceptions;

public class NoUserFound extends RuntimeException {
    public NoUserFound() {
        super();
    }

    public NoUserFound(String message) {
        super(message);
    }

    public NoUserFound(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUserFound(Throwable cause) {
        super(cause);
    }

    protected NoUserFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

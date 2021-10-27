package ir.maktab.userservice.exceptions;

public class TripNotFound extends RuntimeException {
    public TripNotFound() {
        super();
    }

    public TripNotFound(String message) {
        super(message);
    }

    public TripNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public TripNotFound(Throwable cause) {
        super(cause);
    }

    protected TripNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

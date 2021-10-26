package ir.maktab.userservice.exceptions;

public class NoTicketsFound extends RuntimeException {
    public NoTicketsFound() {
        super();
    }

    public NoTicketsFound(String message) {
        super(message);
    }

    public NoTicketsFound(String message, Throwable cause) {
        super(message, cause);
    }

    public NoTicketsFound(Throwable cause) {
        super(cause);
    }

    protected NoTicketsFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

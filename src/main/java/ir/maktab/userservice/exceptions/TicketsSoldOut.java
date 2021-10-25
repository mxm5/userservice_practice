package ir.maktab.userservice.exceptions;

public class TicketsSoldOut extends RuntimeException {
    public TicketsSoldOut() {
        super();
    }

    public TicketsSoldOut(String message) {
        super(message);
    }

    public TicketsSoldOut(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketsSoldOut(Throwable cause) {
        super(cause);
    }

    protected TicketsSoldOut(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

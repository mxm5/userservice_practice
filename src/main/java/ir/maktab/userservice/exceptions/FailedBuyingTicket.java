package ir.maktab.userservice.exceptions;

public class FailedBuyingTicket extends RuntimeException {
    public FailedBuyingTicket() {
        super();
    }

    public FailedBuyingTicket(String message) {
        super(message);
    }

    public FailedBuyingTicket(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedBuyingTicket(Throwable cause) {
        super(cause);
    }

    protected FailedBuyingTicket(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package ir.maktab.userservice.exceptions;

public class GenderNotProvided extends RuntimeException {
    public GenderNotProvided() {
        super();
    }

    public GenderNotProvided(String message) {
        super(message);
    }

    public GenderNotProvided(String message, Throwable cause) {
        super(message, cause);
    }

    public GenderNotProvided(Throwable cause) {
        super(cause);
    }

    protected GenderNotProvided(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

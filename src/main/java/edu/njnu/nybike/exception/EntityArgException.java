package edu.njnu.nybike.exception;

public class EntityArgException extends RuntimeException{
    public EntityArgException() {
    }

    public EntityArgException(String message) {
        super(message);
    }

    public EntityArgException(String message, Throwable cause) {
        super(message, cause);
    }
    public EntityArgException(Throwable cause) {
        super(cause);
    }

    public EntityArgException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

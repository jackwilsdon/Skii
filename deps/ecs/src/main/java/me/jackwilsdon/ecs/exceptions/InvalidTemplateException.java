package me.jackwilsdon.ecs.exceptions;

public class InvalidTemplateException extends RuntimeException {

    public InvalidTemplateException() {
        super();
    }

    public InvalidTemplateException(String message) {
        super(message);
    }

    public InvalidTemplateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTemplateException(Throwable cause) {
        super(cause);
    }

    protected InvalidTemplateException(String message, Throwable cause,
                                        boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

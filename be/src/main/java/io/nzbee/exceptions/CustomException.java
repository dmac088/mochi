package io.nzbee.exceptions;


public final class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomException() {
        super();
    }

    public CustomException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CustomException(final String message) {
        super(message);
    }

    public CustomException(final Throwable cause) {
        super(cause);
    }

}

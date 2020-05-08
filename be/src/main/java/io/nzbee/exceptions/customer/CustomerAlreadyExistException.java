package io.nzbee.exceptions.customer;


public final class CustomerAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public CustomerAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CustomerAlreadyExistException(final String message) {
        super(message);
    }

    public CustomerAlreadyExistException(final Throwable cause) {
        super(cause);
    }

}

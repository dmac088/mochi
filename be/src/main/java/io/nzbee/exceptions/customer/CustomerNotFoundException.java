package io.nzbee.exceptions.customer;


public final class CustomerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException() {
        super();
    }

    public CustomerNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CustomerNotFoundException(final String message) {
        super(message);
    }

    public CustomerNotFoundException(final Throwable cause) {
        super(cause);
    }

}

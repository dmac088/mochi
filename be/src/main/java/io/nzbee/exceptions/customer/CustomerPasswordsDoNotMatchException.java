package io.nzbee.exceptions.customer;


public final class CustomerPasswordsDoNotMatchException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomerPasswordsDoNotMatchException() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    public CustomerPasswordsDoNotMatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CustomerPasswordsDoNotMatchException(final String message) {
        super(message);
    }

    public CustomerPasswordsDoNotMatchException(final Throwable cause) {
        super(cause);
    }

}

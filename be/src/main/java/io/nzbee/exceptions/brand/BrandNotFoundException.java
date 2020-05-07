package io.nzbee.exceptions.brand;


public final class BrandNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BrandNotFoundException() {
        super();
    }

    public BrandNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BrandNotFoundException(final String message) {
        super(message);
    }

    public BrandNotFoundException(final Throwable cause) {
        super(cause);
    }

}

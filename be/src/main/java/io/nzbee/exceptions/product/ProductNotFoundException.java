package io.nzbee.exceptions.product;


public final class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(final String message) {
        super(message);
    }

    public ProductNotFoundException(final Throwable cause) {
        super(cause);
    }

}

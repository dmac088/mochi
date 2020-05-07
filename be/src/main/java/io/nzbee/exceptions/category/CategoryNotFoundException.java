package io.nzbee.exceptions.category;


public final class CategoryNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CategoryNotFoundException() {
        super();
    }

    public CategoryNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFoundException(final String message) {
        super(message);
    }

    public CategoryNotFoundException(final Throwable cause) {
        super(cause);
    }

}

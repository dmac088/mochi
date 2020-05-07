package io.nzbee.exceptions.tag;


public final class TagNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TagNotFoundException() {
        super();
    }

    public TagNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TagNotFoundException(final String message) {
        super(message);
    }

    public TagNotFoundException(final Throwable cause) {
        super(cause);
    }

}

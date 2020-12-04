package io.nzbee.exceptions.promotion;


public final class PromotionNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PromotionNotFoundException() {
        super();
    }

    public PromotionNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PromotionNotFoundException(final String message) {
        super(message);
    }

    public PromotionNotFoundException(final Throwable cause) {
        super(cause);
    }

}

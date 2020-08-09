package io.nzbee.security.events;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;
import io.nzbee.domain.customer.Customer;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final Customer customer;

    public OnRegistrationCompleteEvent(final Customer c, final Locale locale, final String appUrl) {
        super(c);
        this.customer = c;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    //

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public Customer getCustomer() {
        return customer;
    }

}

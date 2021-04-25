package io.nzbee.test.integration.view.beans.bag;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.customer.Customer;
import io.nzbee.test.integration.view.beans.IViewBeanFactory;

public interface IBagDoBeanFactory extends IViewBeanFactory<Bag> {

	Bag getBean(Customer c);

}

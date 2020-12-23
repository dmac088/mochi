package io.nzbee.test.integration.domain.beans.bag;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.customer.Customer;
import io.nzbee.test.integration.domain.beans.IDoBeanFactory;

public interface IBagDoBeanFactory extends IDoBeanFactory<Bag> {

	Bag getBean(Customer c);

}

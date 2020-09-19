package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;

public interface IBagPortService  extends IPortService<Bag> {

	Bag findByCode(String userName);

	Bag findByCode(String locale, String currency, String userName);

}
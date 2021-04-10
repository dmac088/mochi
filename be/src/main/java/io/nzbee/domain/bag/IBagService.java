package io.nzbee.domain.bag;

import io.nzbee.domain.IDomainService;

public interface IBagService extends IDomainService<Bag> {

	Bag findByCode(String userName);

	Bag findByCode(String locale, String currency, String userName);

	void checkAllBagRules(Bag object);

}

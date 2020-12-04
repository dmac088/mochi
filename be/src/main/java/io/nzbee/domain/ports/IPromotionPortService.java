package io.nzbee.domain.ports;

import io.nzbee.domain.promotion.Promotion;

public interface IPromotionPortService extends IPortService<Promotion> {

	Promotion findByCode(String locale, String code);
	
}

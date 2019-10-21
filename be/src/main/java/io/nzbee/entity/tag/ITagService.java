package io.nzbee.entity.tag;

import java.util.Date;
import java.util.List;
import io.nzbee.entity.ILocalizedService;


public interface ITagService  extends ILocalizedService<Tag> {

	List<Tag> findAll(String locale, Double priceStart, Double priceEnd, String priceType, String currency,
			Date priceDateStart, Date priceDateEnd, List<String> categoryCodes, List<String> brandCodes);
	
}

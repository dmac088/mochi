package io.nzbee.entity.tag;

import java.util.Date;
import java.util.List;
import io.nzbee.entity.ILocalizedDao;

public interface ITagDao  extends ILocalizedDao<Tag> {

	List<Tag> findAll(String locale, Double priceStart, Double priceEnd, String priceType, String currency,
			Date priceDateStart, Date priceDateEnd, List<String> categoryCodes, List<String> brandCodes);
}

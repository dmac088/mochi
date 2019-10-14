package io.nzbee.entity.product.tag;

import java.util.Date;
import java.util.List;
import io.nzbee.entity.ILocalizedDao;

public interface IProductTagDao  extends ILocalizedDao<ProductTag> {

	List<ProductTag> findAll(String locale, Double priceStart, Double priceEnd, String priceType, String currency,
			Date priceDateStart, Date priceDateEnd, List<String> categoryCodes, List<String> brandCodes);
}

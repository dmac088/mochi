package io.nzbee.entity.product.tag;

import java.util.Date;
import java.util.List;

public interface IProductTagService {

	List<ProductTag> findAll(String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, List<Long> categoryIds, List<Long> brandIds);
	
}
